package data;

import data.entity.User;
import data.entity.UserData;

import java.util.HashMap;

public class UserDB {
    private static UserDB instance = new UserDB();
    private HashMap<User,UserData> userDataHashMap = new HashMap<>();

    private UserDB() {
        //fill  with fake data
        User user1 = new User("test","test");
        User user2 = new User("user","password");
        UserData userData1 = new UserData();
        UserData userData2 = new UserData();

        userDataHashMap.put(user1,userData1);
        userDataHashMap.put(user2,userData2);
    }


}
