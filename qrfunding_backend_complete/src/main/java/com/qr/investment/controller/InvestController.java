package com.qr.investment.controller;

import com.qr.investment.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invest")
public class InvestController {
    @Autowired private InvestmentService investmentService;

    @GetMapping("/{userId}")
    public String invest(@PathVariable Long userId) {
        investmentService.investForUser(userId);
        return "redirect:/admin/user/" + userId + "/contributions";
    }
}
