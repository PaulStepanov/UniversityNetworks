package data;

import data.entity.MailBoxStat;
import data.entity.Message;
import data.entity.User;
import data.entity.UserData;
import files.FileExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class UserDB {
    private static UserDB instance = new UserDB();
    private HashMap<User, UserData> userDataHashMap = new HashMap<>();

    private UserDB() {
        User user1 = new User("test", "test");
        User user2 = new User("user", "password");
        UserData userData1 = new UserData();
        UserData userData2 = new UserData();

        Message message1 = new Message();
        message1.setContent("message 1 text");
        message1.getHeaders().add("Header: header1");
        Message message2 = new Message();
        message2.setContent("message 2 text");
        message2.getHeaders().add("Header: header2");

        userData1.getMessages().add(message1);
        userData1.getMessages().add(message2);

        try {
            userData1 = FileExecutor.parseDataFoldersToUserData("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        userDataHashMap.put(user1, userData1);
        userDataHashMap.put(user2, userData2);
    }

    public static boolean isUserExistByName(String userName) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        instance.userDataHashMap.keySet().forEach(user -> {
            if (user.getUserName().equals(userName))
                atomicBoolean.set(true);
        });
        return atomicBoolean.get();
    }

    /**
     * Equals method in User must be implemented !!!!!
     *
     * @return null if no user, and return User if username and password are correct
     */
    public static User getUser(String userName, String password) {
        User user = new User(userName, password);
        ArrayList<User> distinctUserArrList = instance.userDataHashMap.keySet().stream()
                .filter(user1 -> user1.equals(user))
                .collect(Collectors.toCollection(ArrayList<User>::new));
        if (distinctUserArrList.size() > 1) {
            throw new RuntimeException("Wtf wrong?");
        }

        if (distinctUserArrList.size() == 0) {
            return null;
        }
        if (distinctUserArrList.size() == 1) {
            return distinctUserArrList.get(0);
        }

        return null;
    }


    public static ArrayList<Message> getUserMessages(User user) {
        return instance.userDataHashMap.get(user).getMessages();
    }

    public static boolean deleteUserMessage(User user, int messageID) {
        try {
            instance.userDataHashMap.get(user).getMessages().get(messageID).setMarkedForDelete(true);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static MailBoxStat getMailBoxStat(User user) {
        MailBoxStat returnedStat = new MailBoxStat();

        ArrayList<Message> messages = instance.userDataHashMap.get(user).getMessages();
        messages = messages.stream().filter(message -> !message.isMarkedForDelete()).collect(Collectors.toCollection(ArrayList::new));
        returnedStat.setSize(messages.stream().reduce(0,
                (accum, message) -> {
                    int length = message.getContent().length();
                    return accum + length;
                },
                (integer, integer2) -> integer));

        returnedStat.setMessagesCount(messages.size());
        return returnedStat;
    }

    public static void saveToFolders(User user){
        ArrayList<Message> userMessages = getUserMessages(user);
        userMessages.stream().reduce(0,(acc,message) -> {
            if (message.isMarkedForDelete())
                userMessages.remove(acc);
            return acc+1;
        },(integer, integer2) -> integer);

        try {
            FileExecutor.saveUserDataToFolders(user.getUserName(),instance.userDataHashMap.get(user));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
