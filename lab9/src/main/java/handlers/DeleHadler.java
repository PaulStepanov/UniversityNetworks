package handlers;

import data.UserDB;
import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
import data.entity.UserDI;

public class DeleHadler extends Handler {
    private final static String PATTERN = "^DELE?.+";
    private UserDI userDI;

    public DeleHadler(UserDI userDI) {
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

        String userData = input.substring(input.indexOf("DELE") + 4, input.length());
        //if message id specified
        Integer messageID;
        try {
            messageID = Integer.valueOf(userData.trim());
        } catch (NumberFormatException e) {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("Bad message id");
            return executeResult;
        }
        if (UserDB.deleteUserMessage(userDI.getUser(), messageID)) {
            executeResult.setExecuteStatus(ExecuteStatus.OK);
            executeResult.setResultMessage("message " + messageID + " has been marked for delete");
            return executeResult;
        } else {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("wrong index");
            return executeResult;
        }
    }
}
