package net.springboot.examples.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.springboot.examples.security.Identity;


@Controller
public class KeycloakController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/protected")
    public String handleProtected(Model model) {
        configCommonAttributes(model);
        return "protected";
    }

    @GetMapping(value = "/protected/premium")
    public String handlePremium(Model model) {
        configCommonAttributes(model);
        return "premium";
    }

    @GetMapping(value = "/logout")
    public String handleLogoutt() throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String handleHome(Model model) throws ServletException {
        configCommonAttributes(model);
        return "home";
    }

    @GetMapping(value = "/accessDenied")
    public String handleAccessDenied() throws ServletException {
        return "access-denied";
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("identity", new Identity(getKeycloakSecurityContext()));
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
