package org.interswitch.onboardingservice.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return user's roles or authorities, if any
        return Collections.emptyList(); // You can implement roles/authorities if required
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement logic as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement logic as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement logic as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement logic as needed
    }
}
