package ipss.group1.practicas.services;

import ipss.group1.practicas.models.AppUserDetails;
import ipss.group1.practicas.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    public CustomUserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUserDetails> user = userDetailsRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        if (user.isPresent()) {
            return User.withUsername(user.get().getUsername())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}