package pl.edu.agh.kis.pz1;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import pl.edu.agh.kis.pz1.util.Config;
public class MainClient {
    public static void main( String[] args ) throws IOException {
        ClientThread clientThread = new ClientThread(new Socket(Config.HOST, Config.PORT));
        String serverMessageType;
        String serverMessage;
        String clientMessage;
        Scanner scanner = new Scanner(System.in);
        while ((serverMessageType = clientThread.in.readLine()) != null) {
            serverMessage = clientThread.in.readLine();
            System.out.println("Server: " + serverMessage);
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
