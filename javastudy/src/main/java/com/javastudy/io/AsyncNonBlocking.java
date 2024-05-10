package com.javastudy.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Def. 비동기-NonBlocking 예제
 * - 요청 작업의 완료여부를 체크하지 않아도 되며 순차적으로 처리 되지 않을 수 있음(비동기)
 * - 요청 작업 완료 후 필요한 처리가 있다면 이벤트나 콜백을 통해 구현됨
 * - 함수를 호출하더라도 호출한 스레드가 차단되지 않음(NonBlocking)
 */
public class AsyncNonBlocking {
    public static void main(String[] args) {
        try {
            // 서버 소켓 채널 생성
            ServerSocketChannel serverSocketChannel = getServerSocketChannel();

            // Selector 생성 및 channel에 등록
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // 이벤트 수신(non blocking)
                selector.selectNow();

                // 이벤트 발생
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    // 클라이언트의 연결 요청이 들어왔다는 이벤트 발생
                    if (key.isAcceptable()) acceptClient(key, selector);
                        // 클라이언트의 요청 데이터가 도착했다는 이벤트 발생
                    else if (key.isReadable()) readClientData(key);
                    System.out.println("메인 스레드의 동작은 멈추지 않음");
                }
            }
        } catch (IOException e) {
        }
    }

    private static ServerSocketChannel getServerSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8000));
        serverSocketChannel.configureBlocking(false); // Non-blocking 모드 설정
        return serverSocketChannel;
    }

    private static void acceptClient(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false); // Non-blocking 모드 설정
        clientChannel.register(selector, SelectionKey.OP_READ); // 읽기 이벤트 등록
        System.out.println("클라이언트 연결됨");
    }

    private static void readClientData(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            clientChannel.close();
            System.out.println("클라이언트 연결 종료됨");
            return;
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println("수신한 데이터: " + new String(data));
    }
}
