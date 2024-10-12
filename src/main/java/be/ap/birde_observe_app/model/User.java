package be.ap.birde_observe_app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bo_usernamen")
    private String username;

    @Column(name = "bo_password")
    private String password;

    @Column(name = "bo_accountNonExpired")
    private Boolean accountNonExpired;

    @Column(name = "bo_accountNonLocked")
    private Boolean accountNonLocked;

    @Column(name = "credentialsNonExpried")
    private Boolean credentialsNonExpried;

    @Column(name = "bo_enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public Boolean getAccountNonExpired() {
    //     return accountNonExpired;
    // }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    // public Boolean getAccountNonLocked() {
    //     return accountNonLocked;
    // }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    // public Boolean getCredentialsNonExpried() {
    //     return credentialsNonExpried;
    // }

    public void setCredentialsNonExpried(Boolean credentialsNonExpried) {
        this.credentialsNonExpried = credentialsNonExpried;
    }

    // public Boolean getEnabled() {
    //     return enabled;
    // }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpried;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    
    
}
