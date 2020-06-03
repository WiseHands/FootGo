package ua.lviv.footgo.auth.service;

import ua.lviv.footgo.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
