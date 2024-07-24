package com.semokincare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "token")
    private String token;

    @Column(name = "redirect_url")
    private String redirectUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @OneToOne(mappedBy = "payment")
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public enum TransactionStatus {
        ORDERED,
        PENDING,
        SETTLEMENT,
        CANCEL,
        DENY,
        EXPIRE,
        FAILURE;
    }
}
