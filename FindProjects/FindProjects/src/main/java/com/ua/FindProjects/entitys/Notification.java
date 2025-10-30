package com.ua.FindProjects.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private boolean isRead;

    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

/*    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/
}