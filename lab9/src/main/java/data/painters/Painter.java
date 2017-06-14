package data.painters;

import data.entity.Message;

import java.util.ArrayList;

/**
 * Created by pili on 6/7/17.
 */
public class Painter {
    public static String stringifyMessages(ArrayList<Message> messages) {
        StringBuilder result = new StringBuilder();
        result.append(messages.size()).append(" messages")
                .append("(")
                //counting octets in messages
                .append(messages.stream().reduce(0,
                        (accum, message) -> {
                            int length = message.getContent().length();
                            return accum+length;
                        },
                        (u, u2) -> u)
                .intValue())
                .append(")").append("\n");

        messages.stream().reduce(0,(count, message) -> {
            result
                    .append(count)
                    .append(" ")
                    .append(message.getContent().length())
                    .append("\n");
            return count+1;
        },(integer, integer2) -> integer);
        return result.toString();
    }
}
