package data.entity;

/**
 * Class for storing user pointer, this is because i have no dependency injection
 */

public class UserDI {
    private User user = null;
    private boolean isLoggined = false;

    public boolean isLoggined() {
        return isLoggined;
    }

    public void setLoggined(boolean loggined) {
        isLoggined = loggined;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
