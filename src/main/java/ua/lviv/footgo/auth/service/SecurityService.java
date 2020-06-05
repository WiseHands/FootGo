package ua.lviv.footgo.auth.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String email, String password);
}
