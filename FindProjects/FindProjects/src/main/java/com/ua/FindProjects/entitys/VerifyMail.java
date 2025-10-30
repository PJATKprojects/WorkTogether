package com.ua.FindProjects.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "verify_mail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;

    @Column(name = "expiry_date")
    LocalDateTime expiryDate;

    @OneToOne(cascade = CascadeType.DETACH)
    User user;
}
