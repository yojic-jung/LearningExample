package com.javastudy.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Def. 동기-블로킹 동작 원리 예제
 */
public class SyncBlockingWithStream {
    public static void main(String[] args) throws IOException {
        // 서버 소켓 생성
        ServerSocket serverSocket = getServerSocket();
        while (true) {
            try {
                System.out.println("1. [main] : accept 메서드에 제어권 전달");
                // 클라이언트 요청 대기 및 승인
                Socket clientSocket = serverSocket.accept();
                System.out.println("2. [main] : accept가 클라이언트 요청 승인하고 제어권 반환");

                // 동기 블로킹 함수 호출
                System.out.println("3. [main] : read 메서드에 제어권 전달");
                String clientData = readClientData(clientSocket);
                System.out.println("5. [main] : 클라이언트 요청 데이터 읽기 종료로 제어권 반환");
                System.out.println("6. [main] : 클라이언트의 요청 데이터 = " + clientData);

                System.out.println("7. [main] : 제어권 되찾음");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 요청 데이터 읽기
    private static String readClientData(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            StringBuilder clientData = new StringBuilder();
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                if (clientData.isEmpty()) System.out.println("4. [stream] : 클라이언트 요청 데이터 읽기 시작");
                clientData.append((char) byteRead);
                if ((char) byteRead == '\n') break;
            }
            return clientData.toString();
        } catch (IOException e) {
            System.out.println("Error reading client data: " + e.getMessage());
            return null;
        }
    }

    // 서버 소켓 생성
    private static ServerSocket getServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress address = new InetSocketAddress("localhost", 8000);
        serverSocket.bind(address);
        return serverSocket;
    }
}
