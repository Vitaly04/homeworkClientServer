package task1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    // выбран блокирующий ввод-вывод, потому что нет смысла делать неблокирующий ввод/вывод
    // клиент будет ожидать решение сервера а потом уже применять вычисление дальше для своих нужд
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 55334);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите целое положительное число для подсчета N-го члена ряда Фибоначчи(для выхода: end): ");
                String msg = scanner.nextLine();
                if ("end".equals(msg)) break;
                out.println(msg);
                System.out.println("Ответ сервера : " + in.readLine());
            }
        }
    }
}
