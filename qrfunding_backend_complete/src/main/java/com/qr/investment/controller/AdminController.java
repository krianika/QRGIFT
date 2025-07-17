package com.qr.investment.controller;

import com.qr.investment.model.User;
import com.qr.investment.model.Contribution;
import com.qr.investment.repository.UserRepository;
import com.qr.investment.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired private UserRepository userRepository;
    @Autowired private ContributionRepository contributionRepository;

    @GetMapping("/users")
    public String users(Model m) {
        List<User> users = userRepository.findAll();
        m.addAttribute("users", users);
        return "admin-users";
    }

    @GetMapping("/user/{id}/contributions")
    public String contributions(@PathVariable Long id, Model m) {
        User u = userRepository.findById(id).orElse(null);
        if(u==null) return "error";
        List<Contribution> cs = contributionRepository.findByUser(u);
        m.addAttribute("user", u);
        m.addAttribute("contributions", cs);
        return "admin-contributions";
    }
}
