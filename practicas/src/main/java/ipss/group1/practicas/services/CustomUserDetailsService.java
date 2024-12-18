package ipss.group1.practicas.services;

import ipss.group1.practicas.models.AppUserDetails;
import ipss.group1.practicas.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserDetails appUserDetails = userDetailsRepository.findById(username).orElse(null);
        if (appUserDetails != null) {
            return User.withUsername(appUserDetails.getUsername())
                    .password(appUserDetails.getPassword())
                    .roles(appUserDetails.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    public void saveUser(AppUserDetails user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsRepository.save(user);
    }
}