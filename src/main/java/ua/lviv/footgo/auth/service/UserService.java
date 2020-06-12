package ua.lviv.footgo.auth.service;

import ua.lviv.footgo.auth.model.User;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    Optional<User> findUserByResetToken(String resetToken);
    void save(User user);
}
