package handlers;

import data.UserDB;
import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
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
    public ExecuteResult handle(String input) {
        ExecuteResult executeResult = new ExecuteResult();

        if (!userDI.isLoggined()) {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("You are not logged");
            return executeResult;
        }

        String userData = input.substring(input.indexOf("RETR") + 4, input.length());
        //if message id specified
        Integer messageID;
        try {
            messageID = Integer.valueOf(userData.trim());
        } catch (NumberFormatException e) {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("Bad message id");
            return executeResult;
        }
        try {
            Message message = UserDB.getUserMessages(userDI.getUser()).get(messageID);
            executeResult.setExecuteStatus(ExecuteStatus.OK);
            executeResult.setResultMessage(message.toString());
            return executeResult;
        } catch (IndexOutOfBoundsException e) {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("Bad message id");
            return executeResult;
        }


    }
}
