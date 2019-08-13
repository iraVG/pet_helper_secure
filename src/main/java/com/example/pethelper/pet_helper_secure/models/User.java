package com.example.pethelper.pet_helper_secure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    @Column(unique = true)
    private String username;
    private String password;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> roles = Arrays.asList(Role.ROLE_USER);

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();

        if (roles.stream().count() >= 1) {
            roles.add(new SimpleGrantedAuthority(this.roles.get(0).toString()));
            return roles;

        } else if (roles.stream().count() >= 2) {
            List<SimpleGrantedAuthority> authorityList =
                    roles.stream()
                            .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                            .collect(Collectors.toList());
            return authorityList;
        } else {
            return null;
        }
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
