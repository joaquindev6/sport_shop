package com.jfarro.app.services.impl;

import com.jfarro.app.models.domains.Client;
import com.jfarro.app.models.domains.User;
import com.jfarro.app.services.ClientService;
import com.jfarro.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserJpaDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        Client client = null;
        String password = null;
        boolean state;

        if (username.contains("@")) {
            client = clientService.findByEmailClient(username);
            password = client.getPassword();
            state = client.getUserHistory().getState() == 1;
        } else {
            user = userService.findByUsernameUser(username);
            password = user.getPassword();
            state = user.getUserHistory().getState() == 1;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user != null) {
            user.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.getCode())));
        }

        if (client != null) {
            authorities.add(new SimpleGrantedAuthority(client.getAuthority()));
        }

        if (authorities.isEmpty()) {
            throw new UsernameNotFoundException("El usuario '".concat(username).concat("' no tiene roles asignados."));
        }

        return new org.springframework.security.core.userdetails.User(username, password, state, true, true, true, authorities);
    }
}
