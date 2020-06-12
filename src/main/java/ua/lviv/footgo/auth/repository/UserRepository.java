package ua.lviv.footgo.auth.repository;

import ua.lviv.footgo.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    Optional<User> findByResetToken(String resetToken);
}
