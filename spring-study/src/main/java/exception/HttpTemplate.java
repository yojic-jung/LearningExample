package exception;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTemplate {
    public int getMethod(String host) throws IOException {
        URL url = new URL(host);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) return 200;
        else return 0;
    }
}
