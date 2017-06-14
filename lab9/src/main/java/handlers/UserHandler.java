package handlers;

import data.UserDB;
import data.entity.User;
import data.entity.UserDI;

public class UserHandler extends Handler {
    private UserDI userDI;
    private final static String PATTERN = "^USER?.+";

    public UserHandler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public String handle(String input) {
        String userData = input.substring(input.indexOf(" ") + 1, input.length());
        String username = userData.trim();
        boolean isUserExists = UserDB.isUserExistByName(userData);
        if (isUserExists){
            userDI.setUser(new User(username,null));
            return "+OK User accepted";
        }
        return "-ERR User not accepted, bad user name";
    }
}
