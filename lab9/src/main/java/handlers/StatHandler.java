package handlers;

import data.UserDB;
import data.entity.*;

public class StatHandler extends Handler {
    private final static String PATTERN = "^STAT?.+";
    private UserDI userDI;

    public StatHandler(UserDI userDI) {
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

        MailBoxStat mailBoxStat = UserDB.getMailBoxStat(userDI.getUser());
        StringBuilder resultSB = new StringBuilder();
        resultSB
                .append(mailBoxStat.getMessagesCount())
                .append(" messages ")
                .append(mailBoxStat.getSize())
                .append(" Octets");

        executeResult.setExecuteStatus(ExecuteStatus.OK);
        executeResult.setResultMessage(resultSB.toString());
        return executeResult;

    }
}
