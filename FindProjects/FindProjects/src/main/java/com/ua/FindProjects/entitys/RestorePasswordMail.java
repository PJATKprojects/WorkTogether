package com.ua.FindProjects.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "restore_password_email")
public class RestorePasswordMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String token;

    private LocalDateTime expiryDate;

/*    @OneToOne(fetch = FetchType.LAZY)
    private User user;*/
}
