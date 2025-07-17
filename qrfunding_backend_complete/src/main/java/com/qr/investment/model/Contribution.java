package com.qr.investment.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Contribution {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String contributorName;
    private String contributorEmail;
    private double amount;
    private String contributorNote;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getContributorEmail() {
        return contributorEmail;
    }

    public void setContributorEmail(String contributorEmail) {
        this.contributorEmail = contributorEmail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContributorNote() {
        return contributorNote;
    }

    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;

    // getters/setters omitted for brevity
}
