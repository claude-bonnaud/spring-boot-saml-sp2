package com.example.springbootsamlsp2;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("permitAll()")
    @GetMapping("/check")
    public String getStatusMessage(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        return "You can display this page of spring-boot-saml-sp2 which is permitted to everybody";
    }

    @PreAuthorize("hasAuthority('Everyone')")
    @GetMapping("/check2")
    public String getStatusMessage2(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        return "You can display this page of spring-boot-saml-sp2 if you belong to the group Everyone";
    }

    @PreAuthorize("hasAuthority('Foreign Exchange Transactions')") 
    @GetMapping("/check3")
    public String getStatusMessage3(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        return "You can display this page of spring-boot-saml-sp2 if you belong to the group Foreign Exchange Transactions";
    }    
}
