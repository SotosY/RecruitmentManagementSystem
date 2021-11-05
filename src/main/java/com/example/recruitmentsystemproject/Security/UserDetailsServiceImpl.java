package com.example.recruitmentsystemproject.Security;

import com.example.recruitmentsystemproject.Business.UserServices.UserReadService;
import com.example.recruitmentsystemproject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Code adapted from examples at https://www.websparrow.org/spring/spring-boot-spring-security-with-jpa-authentication-and-mysql [Accessed: 05 November 2021]
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserReadService userReadService;

    @Autowired
    public UserDetailsServiceImpl(UserReadService userReadService){
        this.userReadService = userReadService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userReadService.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException(email + " cannot be found."));
        return user.map(UserDetailsImpl::new).get();
    }
}
