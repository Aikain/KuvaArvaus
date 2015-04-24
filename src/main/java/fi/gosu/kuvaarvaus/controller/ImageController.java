package fi.gosu.kuvaarvaus.controller;

import fi.gosu.kuvaarvaus.domain.HalfImage;
import fi.gosu.kuvaarvaus.domain.Image;
import fi.gosu.kuvaarvaus.domain.User;
import fi.gosu.kuvaarvaus.repository.HalfImageRepository;
import fi.gosu.kuvaarvaus.repository.ImageRepository;
import fi.gosu.kuvaarvaus.service.UserService;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private HalfImageRepository halfImageRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        User user = userService.getAuthenticatedPerson();
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("images", user.getImages());
        return "images";
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createImage(@RequestParam("file") String file) {
        if (file == null || file.length() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getAuthenticatedPerson();
        if (user == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Image image = new Image();
        image.setContent(DatatypeConverter.parseBase64Binary(file));
        image.setUser(user);
        image = imageRepository.save(image);
        user.getImages().add(image);
        return new ResponseEntity(image, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(
            @RequestHeader(required = false, value = "If-None-Match") String ifNoneMatch,
            @PathVariable String id) {
        if (ifNoneMatch != null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        Image image = imageRepository.findOne(id);
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(image.getContent().length);
        headers.setETag("\"" + image.getId() + "\"");
        headers.setCacheControl("public");
        headers.setExpires(Long.MAX_VALUE);
        return new ResponseEntity<>(image.getContent(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable String id) {
        Image image = imageRepository.findOne(id);
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.getAuthenticatedPerson();
        if (user == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        System.out.println(!user.equals(image.getUser()));
        if (!user.equals(image.getUser())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        for (HalfImage halfImage : image.getHalfImages()) {
            halfImageRepository.delete(halfImage);
        }
        image.getHalfImages().removeAll(image.getHalfImages());
        user.getImages().remove(image);
        imageRepository.delete(image);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/halfImage/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getHalfImage(
            @RequestHeader(required = false, value = "If-None-Match") String ifNoneMatch,
            @PathVariable String id) {
        if (ifNoneMatch != null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        HalfImage image = halfImageRepository.findOne(id);
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(image.getContent().length);
        headers.setETag("\"" + image.getId() + "\"");
        headers.setCacheControl("public");
        headers.setExpires(Long.MAX_VALUE);
        return new ResponseEntity<>(image.getContent(), headers, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(value = "/{id}/halfImage", method = RequestMethod.POST)
    public ResponseEntity createHalfImage(@PathVariable String id, @RequestParam("file") String file, @RequestParam int visibility) {
        if (file == null || file.length() == 0 || visibility < 0 || visibility > 100) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getAuthenticatedPerson();
        if (user == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Image image = imageRepository.findOne(id);
        if (image == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        HalfImage halfImage = new HalfImage();
        halfImage.setContent(DatatypeConverter.parseBase64Binary(file));
        halfImage.setImage(image);
        halfImage.setVisibility(visibility);
        halfImage = halfImageRepository.save(halfImage);
        image.getHalfImages().add(halfImage);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
