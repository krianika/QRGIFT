package com.qr.investment.controller;

import com.qr.investment.model.User;
import com.qr.investment.model.Contribution;
import com.qr.investment.repository.UserRepository;
import com.qr.investment.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contributions")
public class ContributionFormController {
    @Autowired private UserRepository userRepository;
    @Autowired private ContributionRepository contributionRepository;

    @GetMapping("/form")
    public String form(@RequestParam Long userId, Model m) {
        User user = userRepository.findById(userId).orElse(null);
        if(user==null) return "error";
        m.addAttribute("user", user);
        m.addAttribute("contribution", new Contribution());
        return "contribution-form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Contribution c, @RequestParam Long userId, Model m) {
        User u = userRepository.findById(userId).orElse(null);
        if(u==null) return "error";
        c.setUser(u);
        contributionRepository.save(c);
        return "contribution-success";
    }
}
