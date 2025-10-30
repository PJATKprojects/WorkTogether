package com.ua.FindProjects.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "project_images")
@ToString(exclude = "project")
@EqualsAndHashCode(exclude = "project")
@Builder
public class ProjectImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String url;
    private boolean isMain;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;*/
}
