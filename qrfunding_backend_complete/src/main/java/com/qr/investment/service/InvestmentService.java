package com.qr.investment.service;

import com.qr.investment.model.*;
import com.qr.investment.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvestmentService {
    @Autowired private UserRepository userRepository;
    @Autowired private ContributionRepository contributionRepository;
    @Autowired private InvestmentRepository investmentRepository;

    public String invest(Long userId, Double amount) {
        User u = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Investment inv = new Investment();
        inv.setUser(u);
        inv.setAmount(amount);
        inv.setTimestamp(new Date());
        investmentRepository.save(inv);
        return "Invested " + amount;
    }

    public void investForUser(Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Contribution> cs = contributionRepository.findByUser(u);
        double total = cs.stream().mapToDouble(Contribution::getAmount).sum();
        if(total>0) {
            Investment inv = new Investment();
            inv.setUser(u);
            inv.setAmount(total);
            inv.setTimestamp(new Date());
            investmentRepository.save(inv);
            contributionRepository.deleteAll(cs);
        }
    }

    public List<Investment> getByUser(Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return investmentRepository.findByUser(u);
    }
}
