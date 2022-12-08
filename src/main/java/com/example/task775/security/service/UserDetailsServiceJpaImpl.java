package com.example.task775.security.service;

import com.example.task775.security.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailsServiceJpaImpl implements UserDetailsService {

    private static final Set<String> ROLES = Set.of("USER");
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var account = accountRepo.findByName(username);

       if (account != null) {
           var userBuilder = User
                   .withUsername(account.getName())
                   .password(account.getPassword());
           ROLES.forEach(userBuilder::roles);

           return userBuilder.build();
       } else {
           throw new UsernameNotFoundException("Пользователь не найден");
       }
    }
}
