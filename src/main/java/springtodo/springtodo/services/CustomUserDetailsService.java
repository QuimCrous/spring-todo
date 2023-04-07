package springtodo.springtodo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springtodo.springtodo.config.CustomUserDetails;
import springtodo.springtodo.repositories.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userRepository.findByUserName(username).isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return new CustomUserDetails(userRepository.findByUserName(username).get());
    }
}
