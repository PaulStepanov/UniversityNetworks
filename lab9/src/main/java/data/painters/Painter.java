package data.painters;

import data.entity.Message;

import java.util.ArrayList;

/**
 * Created by pili on 6/7/17.
 */
public class Painter {
    public String stringifyMessages(ArrayList<Message> messages) {
        StringBuilder result = new StringBuilder();
        result.append(messages.size()).append(" messages")
                .append("(").append()

        return result.toString();
    }
}
