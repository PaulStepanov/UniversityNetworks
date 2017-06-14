package handlers;

import data.UserDB;
import data.entity.UserDI;

public class DeleHadler extends Handler {
    private final static String PATTERN = "^DELE?.+";
    private UserDI userDI;

    public DeleHadler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public String handle(String input) {
        if (!userDI.isLoggined()) {
            return "-ERR You are not logged";
        }

        String userData = input.substring(input.indexOf("DELE") + 4, input.length());
        //if message id specified
        Integer messageID;
        try {
            messageID = Integer.valueOf(userData.trim());
        } catch (NumberFormatException e) {
            return "-ERR Bad message id";
        }
        if (UserDB.deleteUserMessage(userDI.getUser(), messageID)) {
            return "+OK message " + messageID + " has been deleted";
        } else {
            return "-ERR wrong index";
        }
    }
}
