package handlers;

import data.entity.User;

public class UserHandler implements Handler {
    private User user;

    public UserHandler(User user) {
        this.user = user;
    }

    @Override
    public boolean isThisHandlerSatisfy(String string) {
        return false;
    }

    @Override
    public String handle(String input) {
        return null;
    }
}
