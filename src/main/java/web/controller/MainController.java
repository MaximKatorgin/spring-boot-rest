package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserServiceImp;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/user")
    public String userInfoPage(ModelMap modelMap, @AuthenticationPrincipal User principal) {
        modelMap.addAttribute("principal", principal);
        return "userInfo";
    }

    @GetMapping("/admin")
    public String adminPage(ModelMap model, @AuthenticationPrincipal User principal) {
        model.addAttribute("roles_list", userServiceImp.getRolesList());
        model.addAttribute("principal", principal);
        return "index";
    }

    @GetMapping
    public String homePage() {
        return "redirect:/login";
    }
}
