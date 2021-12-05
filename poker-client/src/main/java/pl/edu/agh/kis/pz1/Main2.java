package pl.edu.agh.kis.pz1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import pl.edu.agh.kis.pz1.util.TextUtils;
public class Main2 {
    public static void main( String[] args ) throws IOException {
        ClientThread clientThread = new ClientThread(new Socket(TextUtils.HOST, TextUtils.PORT));
        String serverMessageType, serverMessage, clientMessage;
        Scanner scanner = new Scanner(System.in);
        while ((serverMessageType = clientThread.in.readLine()) != null) {
            serverMessage = clientThread.in.readLine();
            System.out.println("Server: " + serverMessageType + ": " + serverMessage);
            if (serverMessageType.equals("1")) {
                System.out.println("Enter your message: ");
                clientMessage = scanner.nextLine();
                if (clientMessage != null) {
                    clientThread.out.println(clientMessage);
                }
            }
        }
    }
}
