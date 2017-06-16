package files;

import data.entity.Message;
import data.entity.UserData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by pili on 6/15/17.
 */
public class FileExecutor {
    public static final String PATH_TO_USERS_FOLDERS = "/home/pili/Documents/Programming/Java/UniversityNetworks/lab9/server/userData/";
    public static void saveUserDataToFolders(String username, UserData userData) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(PATH_TO_USERS_FOLDERS))) {
            paths
                    .filter(Files::isDirectory)
                    .filter(path -> path.toFile().getName().equals(username))
                    .forEach(path -> {//for user folder
                        try {
                            Files.walk(path).forEach(path1 -> path1.toFile().delete());//deleting all messages
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        path.toFile().mkdir();
                        //writing messages to file
                        userData.getMessages().stream().reduce(0,(count,message) -> {
                            File file = new File(PATH_TO_USERS_FOLDERS+username+"/message"+count+".msg");
                            try {
                                MessageParser.parseMessageToFile(message,file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return count+1;
                        },(u, u2) -> u);
                    });
        }
    }

    public static UserData parseDataFoldersToUserData(String username) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(PATH_TO_USERS_FOLDERS))) {
            UserData userData = new UserData();
            ArrayList<Message> messages = new ArrayList<>();

            paths
                    .filter(Files::isDirectory)
                    .filter(path -> path.toFile().getName().equals(username))
                    .forEach(path -> {//for user folder
                        try {
                            Files.walk(path)
                                    .filter(Files::isRegularFile)
                                    .forEach(path1 -> {
                                        File file = path1.toFile();
                                        try {
                                            Message message = MessageParser.parseFileToMessage(file);
                                            messages.add(message);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });//deleting all messages
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
            userData.setMessages(messages);
            return userData;
        }
    }
}
