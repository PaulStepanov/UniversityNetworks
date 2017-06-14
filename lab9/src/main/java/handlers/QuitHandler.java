package handlers;

import data.UserDB;
import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
import data.entity.Message;
import data.entity.UserDI;
import data.painters.Painter;

public class QuitHandler extends Handler {
    private final static String PATTERN = "^QUIT?.+";
    private UserDI userDI;

    public QuitHandler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public ExecuteResult handle(String input) {
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setExecuteStatus(ExecuteStatus.OK);
        executeResult.setResultMessage("POP3 server signing off");
        executeResult.setExit(true);

        return executeResult;
    }

}
