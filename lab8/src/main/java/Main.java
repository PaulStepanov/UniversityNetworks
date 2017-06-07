
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {
    public static String URL_PATTERN = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
    public static final long CHECK_TIMOUT = 3;

    public static void main(String[] args) throws Exception {
        ArrayList<String> urls = new ArrayList<>();
        IntStream.range(0, args.length).forEach(i -> {
            //checking if user entered correct url
            if (!args[i].matches(URL_PATTERN)) {
                System.err.println("Enter correct URL");
                return;
            }
            urls.add(args[i]);
        });

        HashMap<String, String> urlContentMap = new HashMap<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (; ; ) {
            TimeUnit.SECONDS.sleep(CHECK_TIMOUT);
            urls.forEach(url -> {
                executorService.execute(() -> {
                    try {
                        String newContent = getUrlSource(url);
                        if (!urlContentMap.containsKey(url)) {
                            urlContentMap.put(url, newContent);
                        }
                        //if data not changed
                        if (urlContentMap.get(url).equals(newContent)) {
                            //do nothing
                        } else {
                            awareUser(url);
                            urlContentMap.put(url, newContent);
                        }

                    } catch (IOException e) {
                        System.err.println("Error with " + url);
                        e.printStackTrace();
                    }
                });
            });
        }


    }

    //convert all page to string
    private static String getUrlSource(String url) throws IOException {
        try {
            URL urlObj = new URL(url);
            try {
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setRequestProperty("Content-Type",
                        "text/plain; charset=utf-8");

                try {
                    BufferedReader urlContentBufferedReader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(), "UTF-8"));
                    String inputLine;
                    StringBuilder result = new StringBuilder();
                    while ((inputLine = urlContentBufferedReader.readLine()) != null)
                        result.append(inputLine);
                    urlContentBufferedReader.close();

                    return result.toString();
                } catch (IOException e) {
                    System.out.println(e);
                }
            } catch (ConnectException e) {
                System.out.println(e);
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        return "";
    }

    private static void awareUser(String url) {
        System.out.println("Url has been changed " + url);
    }

}