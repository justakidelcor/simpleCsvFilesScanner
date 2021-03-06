package com.loyaltygroup.test.filesProcessor.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loyaltygroup.test.filesProcessor.utils.CustomLocalDateTimeSerializer;
import javax.validation.constraints.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
public class Record implements Serializable {
    private static final long serialVersionUID = 3494253937075562820L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @NotNull
    @Column(name="created_when")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime created_when;
    @NotNull
    @Column(name="updatedate")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updateDate;
    @Column(name="transactional_id")
    private String transactionalId;
    @Column(name="amount")
    private String amount;
    @Column(name="user_id")
    private String userId;
    @Column(name="status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_when() {
        return created_when;
    }

    public void setCreated_when(LocalDateTime created_when) {
        this.created_when = created_when;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getTransactionalId() {
        return transactionalId;
    }

    public void setTransactionalId(String transactionalId) {
        this.transactionalId = transactionalId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", created_when=" + created_when +
                ", updateDate=" + updateDate +
                ", transactional_id='" + transactionalId + '\'' +
                ", amount='" + amount + '\'' +
                ", user_id='" + userId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

