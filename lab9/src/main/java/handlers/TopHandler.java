package handlers;

import data.UserDB;
import data.entity.ExecuteResult;
import data.entity.ExecuteStatus;
import data.entity.Message;
import data.entity.UserDI;

public class TopHandler extends Handler{
    private final static String PATTERN = "^TOP?.+";
    private UserDI userDI;

    public TopHandler(UserDI userDI) {
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

        String userData = input.substring(input.indexOf("TOP") + 3, input.length()).trim();
        String messageIDStr = userData.split(" ")[0];
        String rowAmountStr = userData.split(" ")[1];


        //if message id specified
        Integer messageID;
        Integer rowAmount;
        try {
            messageID = Integer.valueOf(messageIDStr.trim());
            rowAmount = Integer.valueOf(rowAmountStr.trim());
        } catch (NumberFormatException e) {
            executeResult.setExecuteStatus(ExecuteStatus.ERR);
            executeResult.setResultMessage("Bad value");
            return executeResult;
        }

        Message message = UserDB.getUserMessages(userDI.getUser())
                .get(messageID);

        StringBuilder retSB = new StringBuilder();

        String[] linesInMessage = message.getContent().split("\\r\\n|\\n|\\r");
        if (rowAmount>linesInMessage.length){
            rowAmount = linesInMessage.length;
        }

        retSB
                .append("message ID: ").append(messageID)
                .append("\n");

        for (int i=0;i<rowAmount;i++){
            retSB.append(linesInMessage[i]).append("\n");
        }

        executeResult.setExecuteStatus(ExecuteStatus.OK);
        executeResult.setResultMessage(retSB.toString());

        return executeResult;
    }

}
