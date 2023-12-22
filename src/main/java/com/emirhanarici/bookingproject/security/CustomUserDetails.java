package com.emirhanarici.bookingproject.security;

import com.emirhanarici.bookingproject.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 4514751530271704280L;

    private final User user;



    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
    }


    public String getPassword() {
        return user.getPassword();
    }


    public String getUsername() {
        return user.getUsername();
    }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }


    public String getEmail() {
        return user.getEmail();
    }


    public Long getId() {
        return user.getId();
    }


    public Map<String, Object> getClaims() {
        return user.getClaims();
    }
}
