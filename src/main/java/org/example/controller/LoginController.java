package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
public class LoginController {

    private final CredentialsService service;

    @Autowired
    public LoginController(CredentialsService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showLogin() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        int authenticatedId = service.getAuthenticatedId(username, password);

        if(authenticatedId != -1) {
            session.setAttribute("employeeId", authenticatedId);
            return "redirect:/dashboard";
        }
        redirectAttributes.addFlashAttribute("error", true);
        return "redirect:/";

    }


}
