package com.examly.springapp.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.examly.springapp.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {

    private String userEmail; 
    private String password; 
    Collection<? extends GrantedAuthority> authorities; 

    public CustomUserDetails(User byUserEmail) { 
        this.userEmail = byUserEmail.getEmail();
        this.password= byUserEmail.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>(); 
        auths.add(new SimpleGrantedAuthority(byUserEmail.getRole())); 
        this.authorities = auths;
    } 

    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return authorities; 
    } 

    @Override 
    public String getPassword() { 
        return password; 
    } 

    @Override 
    public String getUsername() { 
        return userEmail;
    } 
 
    @Override 
    public boolean isAccountNonExpired() { 
        return true; 
    }

    @Override 
    public boolean isAccountNonLocked() { 
        return true; 
    }

    @Override 
    public boolean isCredentialsNonExpired() { 
        return true; 
    }

    @Override 
    public boolean isEnabled() { 
        return true; 
    } 
}
