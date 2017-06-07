package data.entity;

import java.util.ArrayList;

public class Message {
    private ArrayList<String> headers = new ArrayList<>();
    private String content;
    private boolean isMarkedForDelete = false;

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public Message setHeaders(ArrayList<String> headers) {
        this.headers = headers;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isMarkedForDelete() {
        return isMarkedForDelete;
    }

    public Message setMarkedForDelete(boolean markedForDelete) {
        isMarkedForDelete = markedForDelete;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (isMarkedForDelete() != message.isMarkedForDelete()) return false;
        if (getHeaders() != null ? !getHeaders().equals(message.getHeaders()) : message.getHeaders() != null)
            return false;
        return getContent() != null ? getContent().equals(message.getContent()) : message.getContent() == null;

    }

    @Override
    public int hashCode() {
        int result = getHeaders() != null ? getHeaders().hashCode() : 0;
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (isMarkedForDelete() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Headers \n");
        this.headers.forEach(s -> {
            result.append(s).append("\n");
        });
        result.append("Content \n");
        result.append(content).append("\n");
        if (isMarkedForDelete){
            result.append("is marked for delete \n");
        } else {
            result.append("not marked for delete \n");
        }

        return result.toString();
    }
}
