package com.ua.FindProjects.DTOs;

import com.ua.FindProjects.enums.RoleEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserVO {
    private Long id;
    private String name;
    private String email;
    private RoleEnum role;

    private String profilePicturePath;
    private String cvFilePath;

    private String refreshTokenKey;
}
