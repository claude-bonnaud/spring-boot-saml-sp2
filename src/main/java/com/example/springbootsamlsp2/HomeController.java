package com.example.springbootsamlsp2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        return "home";
    }

    @PreAuthorize("hasAuthority('User Administrator')") 
    @RequestMapping("/check")
    public String getStatusMessage(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "check: You can display this page if you belong to the group User Administrator");
        return "message";
    }

    @RequestMapping("/check2")
    public String getStatusMessage2(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "check2: You can display this page");
        return "message";
    }

    @RequestMapping("/check3")
    public String getStatusMessage3(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "check3: You can display this page");
        return "message";
    } 

    // passing a parameter as a path variable
    // http://localhost:8080/check4/123456
    @RequestMapping("/check4/{accountnumber}")
    public String getStatusMessage4(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model, 
                                    @PathVariable("accountnumber") String accountnumber) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "/check4/{accountnumber}: You can display this page");
        model.addAttribute("accountnumber", accountnumber);
        return "account";
    } 

    // passing a parameter in the URL
    // http://localhost:8080/check5?accountnumber=123456
    @RequestMapping("/check5")
    public String getStatusMessage5(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model, 
                                    @RequestParam(name="accountnumber", required = true) String accountnumber) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "/check5?accountnumber={accountnumber}: You can display this page");
        model.addAttribute("accountnumber", accountnumber);
        return "account";
    }     

    @PreAuthorize("hasAuthority('User Administrator')") 
    @RequestMapping("/admin")
    public String admin(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "admin: You can display this page because you belong to the group User Administrator");
        return "message";
    } 

    @PreAuthorize("hasAuthority('CADWAdmApprovePmt')") 
    @RequestMapping("/apppmt")
    public String apppmt(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "apppmt: You can display this page because you belong to the group CADWAdmApprovePmt");
        return "message";
    }

    @PreAuthorize("hasAuthority('CADWAdmConsultPmt')") 
    @RequestMapping("/viewpmt")
    public String viewpmt(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "viewpmt: You can display this page because you belong to the group CADWAdmConsultPmt");
        return "message";
    }

    @PreAuthorize("hasAuthority('Foreign Exchange Transactions')") 
    @RequestMapping("/fx")
    public String fx(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        model.addAttribute("message", "fx: You can display this page because you belong to the group Foreign Exchange Transactions");
        return "message";
    }

    @RequestMapping("/conciliationcheque")
    @ResponseBody
    public String conciliationcheque(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) throws IOException, InterruptedException {

        var values = new HashMap<String, String>() {{
            put("com.bnc.sbi.pres.event.http.HttpEventSequencerFilter.spa", "{1}pWHYqM20td+qI8PVhtL5bQ==");
            put ("iedEventProcessor", "operSignMenuOptionProcessor");
            put ("iedEvent", "create");
            put ("linkSelected", "operSignMenuOptionProcessor");
            put ("controllerAlias", "");
            put ("context", "");
            put ("optionSelected", "mi_operSignMenuOptionProcessor");
            put ("supporting", "");
            put ("eventDefId", "k73");
            put ("initialOptionSelected", "mi_accountSummary");          
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        

        String params = URLEncoder.encode("operSignMenuOptionProcessor|operSignMenuOptionProcessor", StandardCharsets.UTF_8.toString());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://commercial-ti-b.bnc.ca/SBIComWeb/EventDispatch/" + params))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        //HttpClient client = HttpClient.newHttpClient();
        //HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
        
        HttpResponse<String> response = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build()
        .send(request, BodyHandlers.ofString());

        return response.body();
    }     
}

