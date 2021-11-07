package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private static int getFibonacciValue(int n) {
        if (n <= 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else  {
            return getFibonacciValue(n - 1) + getFibonacciValue(n - 2);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(55334);
        Socket socket = serverSocket.accept();
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(
                             socket.getInputStream()))) {
            while (true) {
                final String msg = in.readLine();
                System.out.println("Получено сообщение от клиента: " + msg);
                int fibonacciValue = Integer.parseInt(msg);
                String response = String.valueOf(getFibonacciValue(fibonacciValue));
                out.println(response);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
