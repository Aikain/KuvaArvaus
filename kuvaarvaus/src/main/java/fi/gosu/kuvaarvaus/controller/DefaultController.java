package fi.gosu.kuvaarvaus.controller;

import fi.gosu.kuvaarvaus.domain.User;
import fi.gosu.kuvaarvaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/*")
public class DefaultController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        User user = userService.getAuthenticatedPerson();
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {
        return "signup";
    }
}
