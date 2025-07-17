package com.qr.investment.repository;

import com.qr.investment.model.Contribution;
import com.qr.investment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution,Long> {
    List<Contribution> findByUser(User user);
}
