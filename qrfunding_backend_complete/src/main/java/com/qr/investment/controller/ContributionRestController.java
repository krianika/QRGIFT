package com.qr.investment.controller;

import com.qr.investment.model.Contribution;
import com.qr.investment.repository.ContributionRepository;
import com.qr.investment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contributions")
@CrossOrigin(origins="http://localhost:3001")
public class ContributionRestController {
    @Autowired private ContributionRepository contributionRepository;
    @Autowired private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> contribute(@RequestParam Long userId, @RequestParam Double amount) {
        return userRepository.findById(userId)
          .map(u -> {
             Contribution c = new Contribution();
             c.setUser(u);
             c.setAmount(amount);
             c.setTimestamp(new java.util.Date());
             contributionRepository.save(c);
             return ResponseEntity.ok("Contribution successful");
          }).orElse(ResponseEntity.badRequest().body("User not found"));
    }
}
