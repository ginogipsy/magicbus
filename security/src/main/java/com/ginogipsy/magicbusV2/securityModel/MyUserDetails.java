package com.ginogipsy.magicbusV2.securityModel;

import com.ginogipsy.magicbusV2.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    @Autowired
    private UserDTO userDTO;

    public MyUserDetails(UserDTO user) {
        userDTO =user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String [] roles= userDTO.getRoles().stream().map(r->r.getProfilo().toString()).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDTO.getIsEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userDTO.getIsEnabled();
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
