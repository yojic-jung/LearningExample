package com.javastudy.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SyncNonBlockingWithChannel {
    public static void main(String[] args) {
        try {
            // 서버 소켓 채널 생성
            ServerSocketChannel serverSocketChannel = getServerSocketChannel();
            // 피호출자 스레드 리스트
            List<DataReaderThread> calleeList = new ArrayList<>();

            while (true) {
                // 클라이언트 연결 요청 수락(non blocking)
                SocketChannel clientChannel = serverSocketChannel.accept();
                if (clientChannel != null) {
                    System.out.println("클라이언트 연결됨");

                    // 피호출자 스레드 생성
                    DataReaderThread dataReaderThread = new DataReaderThread(clientChannel);
                    Thread thread = new Thread(dataReaderThread);
                    // 피호출 함수 실행
                    thread.start();
                    // 피호출자 스레드 리스트에 담기
                    calleeList.add(dataReaderThread);
                }

                // 요청 순서대로 피호출자의 작업 완료 체크
                if (!calleeList.isEmpty() && calleeList.get(0).isDone) {
                    // 요청 순서대로 입력된 데이터를 읽음
                    System.out.println("수신한 데이터 : " + calleeList.get(0).getClientData());
                    calleeList.remove(0);
                }

                // 메인 스레드 쉬지 않고 작업함
                if (LocalDateTime.now().getNano() == 0) System.out.println("메인 스레드의 동작은 멈추지 않음");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ServerSocketChannel getServerSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8000));
        serverSocketChannel.configureBlocking(false); // Non-blocking 모드 설정
        return serverSocketChannel;
    }
}
