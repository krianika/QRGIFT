package com.qr.investment.controller;

import com.qr.investment.model.Investment;
import com.qr.investment.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    @Autowired private InvestmentService investmentService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> invest(@PathVariable Long userId, @RequestParam Double amount) {
        try {
            String msg = investmentService.invest(userId, amount);
            return ResponseEntity.ok(java.util.Collections.singletonMap("message", msg));
        } catch(RuntimeException ex) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", ex.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Investment>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(investmentService.getByUser(userId));
    }
}
