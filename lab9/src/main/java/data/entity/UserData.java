package data.entity;

import java.util.ArrayList;

public class UserData {
    private ArrayList<Message> messages = new ArrayList<>();

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public UserData setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Message getMessage(int id){
        return messages.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        return getMessages() != null ? getMessages().equals(userData.getMessages()) : userData.getMessages() == null;

    }

    @Override
    public int hashCode() {
        return getMessages() != null ? getMessages().hashCode() : 0;
    }
}
