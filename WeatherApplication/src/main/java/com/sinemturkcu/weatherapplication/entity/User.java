package com.sinemturkcu.weatherapplication.entity;

import com.sinemturkcu.weatherapplication.entity.base.BaseEntity;
import com.sinemturkcu.weatherapplication.enums.Role;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="USER_ID_SEQ"
    )
    private Long id;

    @Column(name = "FIRSTNAME",length = 50)
    private String firstname;

    @Column(name = "LASTNAME",length = 50)
    private String lastname;

    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "TOKENS")
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

