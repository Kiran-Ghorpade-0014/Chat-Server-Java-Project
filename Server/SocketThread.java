
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * SocketThread
 */
public class SocketThread extends Thread {

    Socket client_socket = null;
    String client_address;
    BufferedReader client_input;
    PrintWriter client_output;

    public SocketThread(Socket client_socket) throws IOException {
        this.client_socket = client_socket;
        this.client_address = client_socket.getRemoteSocketAddress().toString();

        // Client Input
        client_input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        // Client Output : send response to client
        client_output = new PrintWriter(client_socket.getOutputStream(), true);
    }

    void sendMessage(String message) {
        client_output.println(message);
    }

    public void run() {
        try {
            String server_message = ClientRespondThread.encrypt("New User Connected : " + client_address);
            Server.broadcast(server_message, this);

            if (client_socket.isClosed()) {
                System.out.println("Notice : '" + client_address + "' disconnected...");

                client_socket.close();
                return;
            }

            String client_message;

            do {
                client_message = client_input.readLine();
                System.out.println(client_address + " :|->| " + client_message);
                server_message = ClientRespondThread.encrypt(client_address +" : ") + client_message;
                Server.broadcast(server_message, this);
            } while (!client_message.equalsIgnoreCase("exit"));

            Server.removeClient(client_address, this);
        } catch (Exception e) {
            System.out.println("Notice : '" + client_address + "' disconnected...");
            return;
        }
    }
}
