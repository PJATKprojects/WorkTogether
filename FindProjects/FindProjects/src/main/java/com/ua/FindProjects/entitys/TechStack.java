package com.ua.FindProjects.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tech_stack")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    /*@ManyToMany(mappedBy = "techStacks")
    private Set<User> users;*/

    /*@ManyToMany(mappedBy = "techStack")
    private Set<Project> projects;*/
}