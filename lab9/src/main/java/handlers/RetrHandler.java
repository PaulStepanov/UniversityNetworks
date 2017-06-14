package handlers;

import data.UserDB;
import data.entity.Message;
import data.entity.UserDI;

public class RetrHandler extends Handler {
    private final static String PATTERN = "^RETR?.+";
    private UserDI userDI;

    public RetrHandler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public String handle(String input) {
        if (!userDI.isLoggined()) {
            return "-ERR You are not logged";
        }

        String userData = input.substring(input.indexOf("RETR") + 4, input.length());
        //if message id specified
        Integer messageID;
        try {
            messageID = Integer.valueOf(userData.trim());
        } catch (NumberFormatException e) {
            return "-ERR Bad message id";
        }
        try {
            Message message = UserDB.getUserMessages(userDI.getUser()).get(messageID);
            StringBuilder res = new StringBuilder();
            res.append("+OK ").append(message.toString());
            return res.toString();
        } catch (IndexOutOfBoundsException e) {
            return "-ERR Bad message id";
        }


    }
}
