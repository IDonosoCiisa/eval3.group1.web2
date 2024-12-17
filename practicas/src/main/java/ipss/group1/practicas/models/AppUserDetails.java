package ipss.group1.practicas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "users")
public class AppUserDetails {

    @Id
    private String username;
    private String password;
    private String role;


    public AppUserDetails() {
    }
    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public static final class AppUserDetailsBuilder {
        private String username;
        private String password;
        private String role;

        private AppUserDetailsBuilder() {
        }

        public static AppUserDetailsBuilder anAppUserDetails() {
            return new AppUserDetailsBuilder();
        }

        public AppUserDetailsBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public AppUserDetailsBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public AppUserDetailsBuilder withRole(String role) {
            this.role = role;
            return this;
        }

        public AppUserDetails build() {
            AppUserDetails appUserDetails = new AppUserDetails();
            appUserDetails.setUsername(username);
            appUserDetails.setPassword(password);
            appUserDetails.setRole(role);
            return appUserDetails;
        }
    }
}