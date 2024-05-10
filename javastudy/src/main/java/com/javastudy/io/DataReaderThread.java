package com.javastudy.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Def. NonBlocking 함수를 포함한 스레드
 * - SyncNonBlocking 클래스에서 호출하는 스레드
 */
public class DataReaderThread implements Runnable {
    private final SocketChannel clientChannel;
    public boolean isDone = false;
    private String clientData;

    public DataReaderThread(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        StringBuilder sb = new StringBuilder();
        try {
            while (clientChannel.read(buffer) != -1) {
                buffer.flip(); // 읽기 모드
                while (buffer.hasRemaining()) {
                    byte dataByte = buffer.get();
                    char character = (char) dataByte;
                    sb.append(character);
                    if (character == '\n') break;
                }
                buffer.clear();

                if (sb.toString().contains("\n")) break;
            }
            isDone = true;
            clientData = sb.toString();
        } catch (IOException e) {
        }
    }

    public String getClientData() {
        return clientData;
    }
}
