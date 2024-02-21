package exception;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class NumberWriter {
    public void write() {
        String fileName = "numbers.txt";
        try(OutputStream outputStream = new FileOutputStream(fileName)) {
            for (int i = 1; i <= 10; i++) {
                String numberAsString = i + "\n";
                byte[] bytes = numberAsString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            System.out.println("exception throw : " + e.getMessage());
        }
    }
}
