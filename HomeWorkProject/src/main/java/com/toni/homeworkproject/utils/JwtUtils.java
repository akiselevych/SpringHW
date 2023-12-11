package com.toni.homeworkproject.utils;

import com.toni.homeworkproject.domain.JwtAuthentication;
import com.toni.homeworkproject.domain.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {
    public static JwtAuthentication generate(Claims claims){
        JwtAuthentication authentication = new JwtAuthentication();
        authentication.setAuthenticated(true);
        authentication.setEmail(claims.getSubject());
        authentication.setRoles(getRoles(claims));
        return authentication;
    }

    private static Set<Role> getRoles(Claims claims) {
        List<Map<String, String>> rolesList = claims.get("roles", List.class);
        System.out.println(rolesList);
        return rolesList.stream()
                .map(roleMap -> {
                    Role role = new Role();
                    role.setRoleName(roleMap.get("roleName"));
                    return role;
                })
                .collect(Collectors.toSet());
    }

}
