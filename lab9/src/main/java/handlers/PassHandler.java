package handlers;

import data.UserDB;
import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
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
    public ExecuteResult handle(String input) {
        ExecuteResult executeResult = new ExecuteResult();

        //if user didn't entered username
        if (userDI.getUser()==null || userDI.getUser().getUserName() == null){
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("Enter user name first");
            return executeResult;
        }

        String userData = input.substring(input.indexOf(" ") + 1, input.length());
        String userPassword = userData.trim();


        User userFromDB = UserDB.getUser(this.userDI.getUser().getUserName(), userPassword);
        if (userFromDB!=null){
            userDI.setUser(userFromDB);
            userDI.setLoggined(true);
            executeResult.setExecuteStatus(ExecuteStatus.OK);
            executeResult.setResultMessage("Password accepted");
            return executeResult;
        }

        executeResult.setExecuteStatus(ExecuteStatus.ERR);
        executeResult.setResultMessage("Wrong password");
        return executeResult;
    }
}
