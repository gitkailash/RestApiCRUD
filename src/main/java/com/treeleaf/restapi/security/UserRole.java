package com.treeleaf.restapi.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import static com.treeleaf.restapi.security.UserPermission.*;

public enum UserRole {

    //Giving permission on role
    NORMAL(Set.of(USER_READ,USER_WRITE)),
    ADMIN(Set.of(USER_READ,USER_WRITE,USER_DELETE,USER_UPDATE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission ->new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE" + this.name()));
        return permissions;
    }
}
