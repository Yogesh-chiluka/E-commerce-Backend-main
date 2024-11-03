package com.backend.Ecommerce.Backend.response.security.user;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable; 

public interface UserDetails extends Serializable {
    
    Collection<? extends GrantedAuthority> getAuthorities();


    String getPassword();

    String getUsername();

    default boolean isAccountNonLocked() { return true;}

    default boolean isAccountNonExpired() { return true;}

    default boolean isCredentialsNonExpired() { return true;}

    default boolean isEnabled() { return true;}
}
