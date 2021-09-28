package com.ginogipsy.magicbus.securityModel;

import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetail")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user=userMapper.findUserByUsername(s);
        if(user==null) throw new UsernameNotFoundException("Utente non trovato");
        return new MyUserDetails(user);
    }
}