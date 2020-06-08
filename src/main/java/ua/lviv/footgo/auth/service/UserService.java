package ua.lviv.footgo.auth.service;

import ua.lviv.footgo.auth.model.User;

public interface UserService {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    void save(User user);
}
