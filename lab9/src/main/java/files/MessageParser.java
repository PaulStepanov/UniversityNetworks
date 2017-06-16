package files;

import data.entity.Message;

import java.io.*;

/**
 * Created by pili on 6/15/17.
 */
public class MessageParser {
    /**
     * Parses file to message
     * */
    public static Message parseFileToMessage(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Message message = null;
        try {
            message = (Message) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * Parses message to xml format and write to file
     * */
    public static void parseMessageToFile(Message message, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
