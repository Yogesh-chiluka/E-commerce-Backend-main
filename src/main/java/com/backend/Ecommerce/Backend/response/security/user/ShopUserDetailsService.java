package com.backend.Ecommerce.Backend.response.security.user;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;  
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.backend.Ecommerce.Backend.model.User;
import com.backend.Ecommerce.Backend.repository.UserRepository;
import java.util.*;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    @Override
    public  UserDetails loadUserByUsername(String email) throws  UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return ShopUserDetails.buildUserDetails(user);
    }
}
