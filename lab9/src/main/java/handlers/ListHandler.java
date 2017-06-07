package handlers;

import data.UserDB;
import data.entity.Message;
import data.entity.User;
import data.entity.UserDI;

/**
 * Created by pili on 6/7/17.
 */
public class ListHandler extends Handler {
    private final static String PATTERN = "^LIST?.+";
    private UserDI userDI;

    public ListHandler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public String handle(String input) {
        if (!userDI.isLoggined()) {
            return "You are not lagged";
        }

        String userData = input.substring(input.indexOf(" ") + 1, input.length());
        if(userData.trim().equals("")){
            //if no arguments

            return "";
        } else {
            //if message id specified
            Integer messageID;
            try {
                messageID= Integer.valueOf(userData.trim());
            }
            catch (NumberFormatException e){
                return "Bad message id";
            }

            Message message = UserDB.getUserMessages(userDI.getUser()).get(messageID);
            return message.toString();
        }

    }
}
