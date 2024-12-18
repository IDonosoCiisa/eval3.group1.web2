package ipss.group1.practicas.config;

import ipss.group1.practicas.models.AppUserDetails;
import ipss.group1.practicas.services.CustomUserDetailsService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({ "dataSource" })
public class InitData {

    private final CustomUserDetailsService customUserDetailsService;

    public InitData(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostConstruct
    public void populateSampleData() {
        customUserDetailsService.saveUser(AppUserDetails.AppUserDetailsBuilder.anAppUserDetails()
                .withUsername("admin")
                .withPassword("adminPassword")
                .withRole("ADMIN")
                .build());
        customUserDetailsService.saveUser(AppUserDetails.AppUserDetailsBuilder.anAppUserDetails()
                .withUsername("estudiante")
                .withPassword("estudiantePassword")
                .withRole("ESTUDIANTE")
                .build());
        customUserDetailsService.saveUser(AppUserDetails.AppUserDetailsBuilder.anAppUserDetails()
                .withUsername("profesor")
                .withPassword("profesorPassword")
                .withRole("Profesor")
                .build());
    }
}