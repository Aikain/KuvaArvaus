package fi.gosu.kuvaarvaus.controller;

import fi.gosu.kuvaarvaus.domain.User;
import fi.gosu.kuvaarvaus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/login";
    }
}
