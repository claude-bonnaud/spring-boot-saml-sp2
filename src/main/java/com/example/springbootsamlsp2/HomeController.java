package com.example.springbootsamlsp2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        return "home";
    }

    @GetMapping("/check")
    public String getStatusMessage(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        return "check";
    }

    @GetMapping("/check2")
    public String getStatusMessage2(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        return "check";
    }

    @GetMapping("/check3")
    public String getStatusMessage3(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        return "check";
    }    
}
