package data;

import data.entity.Message;
import data.entity.User;
import data.entity.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class UserDB {
    private static UserDB instance = new UserDB();
    private HashMap<User, UserData> userDataHashMap = new HashMap<>();

    private UserDB() {
        //fill  with fake data
        User user1 = new User("test", "test");
        User user2 = new User("user", "password");
        UserData userData1 = new UserData();
        UserData userData2 = new UserData();

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


    public static ArrayList<Message> getUserMessages(User user){
        return instance.userDataHashMap.get(user).getMessages();
    }

}
