package handlers;

import data.UserDB;
import data.entity.Message;
import data.entity.User;
import data.entity.UserDI;
import data.painters.Painter;

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
            return "You are not logged";
        }

        String userData = input.substring(input.indexOf("LIST") + 4, input.length());
        if(userData.trim().equals("")){
            //if no arguments
            return Painter.stringifyMessages(UserDB.getUserMessages(userDI.getUser()));
        } else {
            //if message id specified
            Integer messageID;
            try {
                messageID= Integer.valueOf(userData.trim());
            }
            catch (NumberFormatException e){
                return "-ERR Bad message id";
            }
            try{
                Message message = UserDB.getUserMessages(userDI.getUser()).get(messageID);
                StringBuilder res = new StringBuilder();
                res.append("+OK ").append(message.toString());
                return res.toString();
            } catch (IndexOutOfBoundsException e){
                return "-ERR Bad message id";
            }

        }

    }
}
