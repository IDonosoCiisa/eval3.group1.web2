package ipss.group1.practicas.repositories;

import ipss.group1.practicas.models.AppUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<AppUserDetails, String> {
}