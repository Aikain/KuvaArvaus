package fi.gosu.kuvaarvaus.controller;

import fi.gosu.kuvaarvaus.domain.HalfImage;
import fi.gosu.kuvaarvaus.domain.Image;
import fi.gosu.kuvaarvaus.domain.SingleLink;
import fi.gosu.kuvaarvaus.domain.User;
import fi.gosu.kuvaarvaus.repository.HalfImageRepository;
import fi.gosu.kuvaarvaus.repository.ImageRepository;
import fi.gosu.kuvaarvaus.repository.SingleLinkRepository;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private HalfImageRepository halfImageRepository;

    @Autowired
    private SingleLinkRepository singleLinkRepository;

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
    public ResponseEntity createImage(@RequestParam("file") String file, @RequestParam(required = false) String name) {
        if (file == null || file.length() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getAuthenticatedPerson();
        if (user == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (name == null || name.isEmpty()) {
            name = UUID.randomUUID().toString().substring(0, 8);
        }
        Image image = new Image();
        image.setName(name);
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
        if (!user.equals(image.getUser())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        user.getImages().remove(image);
        image.setUser(null);
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

    @RequestMapping(value = "/singleLink/{id}", method = RequestMethod.GET)
    public String redirectToImage(@PathVariable String id) {
        SingleLink singleLink = singleLinkRepository.findOne(id);
        if (singleLink == null || singleLink.getChangeoverTimes().isEmpty()) {
            return null;
        }
        String singleLinkId = "";
        Date singleLinkTime = new Date(0);
        for (Map.Entry<String, Date> entry : singleLink.getChangeoverTimes().entrySet()) {
            if (entry.getValue().after(singleLinkTime) && entry.getValue().before(new Date(System.currentTimeMillis()))) {
                singleLinkId = entry.getKey();
                singleLinkTime = entry.getValue();
            }
        }
        if (singleLinkId.isEmpty()) return "redirect:/resources/public/notReady.png";
        if (singleLinkId.equals("end")) {
            Image image = imageRepository.findBySingleLink_id(singleLink.getId());
            return "redirect:/images/" + image.getId() + ".png";
        }
        return "redirect:/images/halfImage/" + singleLinkId + ".png";
    }

    @Transactional
    @RequestMapping(value = "/{id}/singleLink", method = RequestMethod.POST)
    public ResponseEntity createSingleLink(@PathVariable String id, @RequestParam Map<String, String> times) throws ParseException {
        Image image = imageRepository.findOne(id);
        if (image == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        SingleLink singleLink = image.getSingleLink();
        SimpleDateFormat formatter = new SimpleDateFormat("y-M-d H:m");
        for (Map.Entry<String, String> entry : times.entrySet()) {
            singleLink.getChangeoverTimes().put(entry.getKey(), formatter.parse(entry.getValue()));
        }
        singleLink.setImage(image);
        singleLink = singleLinkRepository.save(singleLink);
        image.setSingleLink(singleLink);
        return new ResponseEntity<>(singleLink.getId(), HttpStatus.OK);
    }
}
