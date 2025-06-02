package ru.nnov.app.model;

public class User {
    private static User user;
    private String username;
    private String jwtAccessToken;
    private String jwtRefreshToken;
    private String firstname;
    private String lastname;

    private User() {}

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public static User getAuthorityUser() {
        return user;
    }
}
