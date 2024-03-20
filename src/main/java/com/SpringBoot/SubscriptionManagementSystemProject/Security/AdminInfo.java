package com.SpringBoot.SubscriptionManagementSystemProject.Security;



import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Admin;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AdminInfo implements UserDetails {
    private String adminUsername;
    private String adminPassword;
    private List<GrantedAuthority> authorities;
    public AdminInfo(Admin admin) {
        this.adminUsername = admin.getAdminUsername();
        this.adminPassword = admin.getAdminPassword();
        this.authorities = Arrays.stream(admin.getRole().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  authorities;
    }

    @Override
    public String getPassword() {
        return adminPassword;
    }

    @Override
    public String getUsername() {
        return adminUsername;
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
