package files;

import data.UserDB;
import data.entity.Message;
import data.entity.User;
import data.entity.UserData;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by pili on 6/15/17.
 */

public class FileExecutorTest {
    @Test
    public void saveUserDataToFolders1() throws Exception {
        File k = new File("k");
        User user = UserDB.getUser("test", "test");
        ArrayList<Message> userMessages = UserDB.getUserMessages(user);
        MessageParser.parseMessageToFile(userMessages.get(0),k);
        UserDB.saveToFolders(user);

        UserData test = FileExecutor.parseDataFoldersToUserData("test");
        test.getMessages().forEach(message -> System.out.println(message.toString()));
    }



}
