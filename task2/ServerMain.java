package task2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerMain {
    private static String deleteSpace(String input) {
        return input.replaceAll("\\s+","");
    }

    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 55334));
        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                while (socketChannel.isConnected()) {
                    int bytesCount = socketChannel.read(inputBuffer);
                    if (bytesCount == -1) break;
                    final String msg = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                    System.out.println("Получено сообщение от клиента: " + msg);
                    String response = deleteSpace(msg);
                    socketChannel.write(ByteBuffer.wrap(("Ответ сервера: " + response).getBytes(StandardCharsets.UTF_8)));
                    inputBuffer.clear();
                }
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
