package handlers;

import data.UserDB;
import data.entity.MailBoxStat;
import data.entity.Message;
import data.entity.UserDI;

public class StatHandler extends Handler {
    private final static String PATTERN = "^STAT?.+";
    private UserDI userDI;

    public StatHandler(UserDI userDI) {
        super(PATTERN);
        this.userDI = userDI;
    }

    @Override
    public String handle(String input) {
        if (!userDI.isLoggined()) {
            return "-ERR You are not logged";
        }

        MailBoxStat mailBoxStat = UserDB.getMailBoxStat(userDI.getUser());
        StringBuilder resultSB = new StringBuilder();
        resultSB
                .append("+OK ")
                .append(mailBoxStat.getMessagesCount())
                .append(" messages ")
                .append(mailBoxStat.getSize())
                .append(" Octets");

        return resultSB.toString();

    }
}
