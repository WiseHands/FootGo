package ua.lviv.footgo.auth.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
