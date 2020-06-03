package ua.lviv.footgo.auth.repository;

import ua.lviv.footgo.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
