package com.qr.investment.repository;

import com.qr.investment.model.Investment;
import com.qr.investment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment,Long> {
    List<Investment> findByUser(User user);
}
