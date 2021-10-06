package net.springboot.examples;

import java.security.Principal;
import java.util.Map;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class CustomUserAttrController {
    @GetMapping(path = "/userInfo")
    public String getUserInfo(Model model) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) 
          SecurityContextHolder.getContext().getAuthentication();
        
        Principal principal = (Principal) authentication.getPrincipal();        
        String dob="";
        
        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<?> kPrincipal = (KeycloakPrincipal<?>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();

            Map<String, Object> customClaims = token.getOtherClaims();

            if (customClaims.containsKey("DOB")) {
                dob = String.valueOf(customClaims.get("DOB"));
            }
        }
        
        model.addAttribute("username", principal.getName());
        model.addAttribute("dob", dob);
        return "user-info";
    }  
}
