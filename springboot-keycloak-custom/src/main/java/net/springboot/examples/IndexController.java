package net.springboot.examples;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private HttpServletRequest request;
    
    @GetMapping
    public String getUserInfo(Model model) {
        return "index";
    }

    @GetMapping(value = "/logout")
    public String handleLogout() throws ServletException{
        request.logout();
        return "redirect:/";
    }
}
