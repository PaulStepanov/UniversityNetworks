package handlers;

import data.UserDB;
import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
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
    public ExecuteResult handle(String input) {
        ExecuteResult executeResult = new ExecuteResult();

        String userData = input.substring(input.indexOf(" ") + 1, input.length());
        String username = userData.trim();
        boolean isUserExists = UserDB.isUserExistByName(userData);
        if (isUserExists){
            userDI.setUser(new User(username,null));
            executeResult.setExecuteStatus(ExecuteStatus.OK);
            executeResult.setResultMessage("User accepted");
            return executeResult;
        }

        executeResult.setExecuteStatus(ExecuteStatus.ERR);
        executeResult.setResultMessage("User not accepted, bad user name");
        return executeResult;
    }
}
