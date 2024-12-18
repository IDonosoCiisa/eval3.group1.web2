package ipss.group1.practicas.controllers.mantainer;

import org.springframework.web.bind.annotation.RestController;

import ipss.group1.practicas.models.AppUserDetails;
import ipss.group1.practicas.services.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class UserController {

    private final CustomUserDetailsService userDetailsService;

    public UserController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<AppUserDetails> createUser(@RequestBody AppUserDetails user) {
        userDetailsService.saveUser(user);
        return ResponseEntity.ok(user);
    }

}