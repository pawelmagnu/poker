package pl.edu.agh.kis.pz1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    public Player player;
    PrintWriter out;
    BufferedReader in;
    Socket clientSocket;

    public ClientThread(Socket socket) throws IOException {
        super();
        this.clientSocket = socket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = new Player();
//        sendMessageWOR("Hello");
//        System.out.println("nowy gracz: " + player.getPlayerID());
    }
    public void showCards() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < player.getHand().getSize(); j++) {
            sb.append(player.getHand().getCard(j).toString()).append(",");
        }
        sendMessageWOR(sb.toString());
    }
    public void sendMessageWOR(String message) {
        out.println(-1);
        out.println(message);
    }
    public String sendMessage(String message) throws IOException {
        out.println(1);
        out.println(message);
        return in.readLine();
    }
}
