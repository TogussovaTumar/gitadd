package kz.AsylbekSpring.springbootAsyl.services;

import kz.AsylbekSpring.springbootAsyl.model.User;
import kz.AsylbekSpring.springbootAsyl.repository.RoleRepository;
import kz.AsylbekSpring.springbootAsyl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}


