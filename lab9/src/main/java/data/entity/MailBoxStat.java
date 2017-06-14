package data.entity;

public class MailBoxStat {
    private int size;
    private int messagesCount;

    public MailBoxStat(int messagesCount, int size) {
        this.messagesCount = messagesCount;
        this.size = size;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public MailBoxStat(){

    }

    public MailBoxStat setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
        return this;
    }

    public int getSize() {
        return size;
    }

    public MailBoxStat setSize(int size) {
        this.size = size;
        return this;
    }
}
