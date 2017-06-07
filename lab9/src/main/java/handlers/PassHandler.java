package handlers;

import data.UserDB;
import data.entity.User;
import data.entity.UserDI;

/**
 * Created by pili on 6/7/17.
 */
public class PassHandler extends Handler {
    private final static String PATTERN = "^PASS?.+";
    private UserDI userDI;

    public PassHandler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public String handle(String input) {
        //if user didn't entered username
        if (userDI.getUser()==null || userDI.getUser().getUserName() == null)
            return "Enter user name first";

        String userData = input.substring(input.indexOf(" ") + 1, input.length());
        String userPassword = userData.trim();


        User userFromDB = UserDB.getUser(this.userDI.getUser().getUserName(), userPassword);
        if (userFromDB!=null){
            userDI.setUser(userFromDB);
            userDI.setLoggined(true);
            return "Password accepted";
        }

        return "Wrong password";
    }
}
