package data.entity;

public class ExecuteResult {
    private String resultMessage;
    private ExecuteStatus executeStatus;
    private boolean isExit = false;//mark that need to close conection with user

    public ExecuteResult(String resultMessage, ExecuteStatus executeStatus, boolean isExit) {
        this.resultMessage = resultMessage;
        this.executeStatus = executeStatus;
        this.isExit = isExit;
    }

    public ExecuteResult(){

    }

    public String getResultMessage() {
        return resultMessage;
    }

    public ExecuteResult setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        return this;
    }

    public ExecuteStatus getExecuteStatus() {
        return executeStatus;
    }

    public ExecuteResult setExecuteStatus(ExecuteStatus executeStatus) {
        this.executeStatus = executeStatus;
        return this;
    }

    public boolean isExit() {
        return isExit;
    }

    public ExecuteResult setExit(boolean exit) {
        isExit = exit;
        return this;
    }
}
