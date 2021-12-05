package pl.edu.agh.kis.pz1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class representing client socket.
 */
public class ClientThread extends Thread {
    public Player player;
    PrintWriter out;
    BufferedReader in;
    Socket clientSocket;

    /**
     * Constructor of ClientThread class.
     * @param socket socket of client
     */
    public ClientThread(Socket socket) {
        super();
        this.clientSocket = socket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error while creating streams");
        }
        player = new Player();
    }

    /**
     * Converts player hand to string and sends it to them via socket.
     */
    public void showCards() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < player.getHand().getSize(); j++) {
            sb.append(player.getHand().getCard(j).toString()).append(",");
        }
        sendMessageWOR(sb.toString());
    }

    /**
     * Sends message to client without response.
     * @param message message to send
     */
    public void sendMessageWOR(String message) {
        out.println(-1);
        out.println(message);
    }

    /**
     * Sends message to client with response.
     * @param message message to send
     * @return response from client
     * @throws IOException in case of error
     */
    public String sendMessage(String message) throws IOException {
        out.println(1);
        out.println(message);
        return in.readLine();
    }
}
