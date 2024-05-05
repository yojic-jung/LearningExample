package com.javastudy.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelExample {
    public static void main(String[] args) {
        try {
            // Selector 생성
            Selector selector = Selector.open();

            // ServerSocketChannel 생성 및 바인딩
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8000));
            serverSocketChannel.configureBlocking(false); // Non-blocking 모드 설정
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // Accept 이벤트 등록

            while (true) {
                // 이벤트를 기다림
                selector.select();

                // 발생한 이벤트를 처리
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        // 클라이언트의 연결 요청이 도착한 경우
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = channel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ); // Read 이벤트 등록
                        System.out.println("클라이언트 연결됨: " + clientChannel.getRemoteAddress());
                    } else if (key.isReadable()) {
                        // 클라이언트로부터 데이터를 읽어들이는 경우
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = clientChannel.read(buffer);
                        if (bytesRead == -1) {
                            // 클라이언트가 연결을 끊은 경우
                            clientChannel.close();
                            key.cancel();
                            System.out.println("클라이언트 연결 종료");
                        } else {
                            // 데이터를 읽어들임
                            buffer.flip();
                            byte[] data = new byte[buffer.remaining()];
                            buffer.get(data);
                            System.out.println("수신한 데이터: " + new String(data));
                            buffer.clear();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
