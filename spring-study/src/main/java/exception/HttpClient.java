//package exception;
//
//import java.io.IOException;
//
//public class HttpClient {
//    private static HttpTemplate httpTemplate = new HttpTemplate();
//    private static final int MAX_RETRY = 3;
//    private static final int RETRY_INTERVAL_MS = 1000; // 1초
//
//    public static void main(String[] args) {
//        String host = "https://newtworkcommunicationexception.com/";
//        int retryCount = 0;
//        boolean success = false;
//
//        while (retryCount < MAX_RETRY) {
//            try {
//                httpTemplate.getMethod(host);
//            } catch (IOException e) {
//                retryCount++;
//                try {
//                    Thread.sleep(RETRY_INTERVAL_MS); // 재시도 전 대기
//                } catch (InterruptedException ex) {
//                    System.err.println("재시도 대기 오류: " + ex.getMessage());
//                }
//            }
//        }
//        if (!success)  System.out.println("Maximum retry count over");
//    }
//}
