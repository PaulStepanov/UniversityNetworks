package handlers;

import data.UserDB;
import data.entity.*;
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
    public ExecuteResult handle(String input) {
        ExecuteResult executeResult = new ExecuteResult();

        if (!userDI.isLoggined()) {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("You are not logged");
            return executeResult;
        }

        String userData = input.substring(input.indexOf("LIST") + 4, input.length());
        if(userData.trim().equals("")){
            //if no arguments
            executeResult.setExecuteStatus(ExecuteStatus.OK);
            executeResult.setResultMessage(Painter.stringifyMessages(UserDB.getUserMessages(userDI.getUser())));
            return executeResult;
        } else {
            //if message id specified
            Integer messageID;
            try {
                messageID= Integer.valueOf(userData.trim());
            }
            catch (NumberFormatException e){
                executeResult.setExecuteStatus(ExecuteStatus.ERR);
                executeResult.setResultMessage("Bad message id");
                return executeResult;
            }
            try{
                Message message = UserDB.getUserMessages(userDI.getUser()).get(messageID);
                StringBuilder res = new StringBuilder();
                executeResult.setExecuteStatus(ExecuteStatus.OK);
                executeResult.setResultMessage(message.toString());
                return executeResult;
            } catch (IndexOutOfBoundsException e){
                executeResult.setExecuteStatus(ExecuteStatus.ERR);
                executeResult.setResultMessage("Bad message id");
                return executeResult;
            }

        }

    }
}
