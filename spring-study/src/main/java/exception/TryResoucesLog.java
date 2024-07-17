package exception;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TryResoucesLog {

    public static void main(String[] args) throws IOException {
        tryCatch();
    }

    public static void tryResources() {
        String fileName = "numbers.txt";
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            throw new NullPointerException();
        } catch (Exception e) {
        } finally {
            throw new IllegalArgumentException();
        }
    }

    public static void tryCatch() throws IOException {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
        } finally {
            throw new IllegalArgumentException();

        }
    }
}
