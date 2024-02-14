import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ResponseThread
 */
public class ServerRespondThread extends Thread {

    Socket client_socket = null;
    String client_address;

    public ServerRespondThread(Socket client_socket) {
        this.client_socket = client_socket;
        this.client_address = client_socket.getRemoteSocketAddress().toString();
    }

    public void run() {

        try {
            // Client Input : takes input from client
            BufferedReader server_input = new BufferedReader(new InputStreamReader(System.in));
            // Client Output : send response to client
            PrintWriter client_output = new PrintWriter(client_socket.getOutputStream(), true);

            String server_message;

            while (true) {
                
                if (client_socket.isClosed()) {
                    System.out.println("Notice : '" + client_address + "' disconnected...");
                    
                    server_input.close();
                    client_output.flush();
                    client_output.close();
                    client_socket.close();
                    return;
                }

                while ((server_message = server_input.readLine())!= null) {
                    // send server message to client
                    System.out.println(client_address+" :<-| " + server_message);
                    client_output.println(server_message);
                }
            }
        } catch (Exception e) {
            System.out.println("Notice : '" + client_address + "' disconnected...");
            return;
        }
    }

}
