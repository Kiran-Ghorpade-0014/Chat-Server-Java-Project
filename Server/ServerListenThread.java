import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ServerListenThread
 */
public class ServerListenThread extends Thread{

    Socket client_socket = null;
    String client_address;

    public ServerListenThread(Socket client_socket) {
        this.client_socket = client_socket;
        this.client_address = client_socket.getRemoteSocketAddress().toString();
    }

    public void run() {

        try {
            // Client Input
            BufferedReader client_input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            // Client Output : send response to client
            PrintWriter client_output = new PrintWriter(client_socket.getOutputStream(), true);

            while (true) {
                String client_message;

                if (client_socket.isClosed()) {
                    System.out.println("Notice : '" + client_address + "' disconnected...");

                    client_socket.close();
                    return;
                }

                while ((client_message = client_input.readLine()) != null) {
                    // send message to all clients message
                    client_output.println(client_address + " :\n|->| " + client_message);
                    // log client activity to console
                    System.out.println(client_address + " :|->| " + client_message);
                }
            }
        } catch (Exception e) {
            System.out.println("Notice : '" + client_address + "' disconnected...");
            return;
        }
    }
}