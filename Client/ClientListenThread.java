import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * ClientListenThread
 */
public class ClientListenThread extends Thread{

    Socket server_socket = null;
    String server_address;

    public ClientListenThread(Socket server_socket) {
        this.server_socket = server_socket;
        this.server_address = server_socket.getRemoteSocketAddress().toString();
    }

    public void run() {

        try {
            // Client Input
            BufferedReader server_input = new BufferedReader(new InputStreamReader(server_socket.getInputStream()));

            while (true) {
                String server_message;

                if (server_socket.isClosed()) {
                    System.out.println("Notice : Server disconnected...");

                    server_socket.close();
                    return;
                }

                while ((server_message = server_input.readLine()) != null) {
                    // log client activity to console
                    System.out.println(server_message);
                }
            }
        } catch (Exception e) {
            System.out.println("Notice : Server Connection Failed...");
            return;
        }
    }
}