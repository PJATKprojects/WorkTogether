package com.ua.FindProjects.entitys;

import com.ua.FindProjects.enums.RoleEnum;
import com.ua.FindProjects.enums.UserStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@EqualsAndHashCode(
        exclude = {"verifyEmail", "ownSecurity", "restorePasswordEmail", "followers", "following"})
@ToString(
        exclude = {"verifyEmail", "ownSecurity", "restorePasswordEmail", "followers", "following"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String name;

    @Min(13)
    @Max(120)
    private int age;

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    private String city;

    private LocalDateTime lastSeen;

    @Column(name="profile_picture_path")
    private String profilePicturePath;

    @Email(message = "incorrect email format")
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum userStatusEnum;

    private LocalDateTime dateOfRegistration;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private VerifyMail verifyMail;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RestorePasswordMail restorePasswordMail;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private OwnSecurity ownSecurity;

    @Min(0)
    @Max(5)
    private Double projectOrganizerRating;

    @Column(name = "cv_file_path")
    private String cvFilePath;

    @ElementCollection
    private List<String> onlineLinks;

    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following;

    @ManyToMany(mappedBy = "following")
    private Set<User> followers;

    @ManyToMany
    @JoinTable(
            name = "user_techstack",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "techstack_id")
    )
    private Set<TechStack> techStacks;

    @OneToMany(mappedBy = "creator")
    private Set<Project> createdProjects;

    @ManyToMany(mappedBy = "participants")
    private Set<Project> projectsParticipated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return ownSecurity!=null ? ownSecurity.getPassword() : "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return userStatusEnum!=UserStatusEnum.DEACTIVATED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userStatusEnum!=UserStatusEnum.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userStatusEnum==UserStatusEnum.ACTIVATED;
    }
}
