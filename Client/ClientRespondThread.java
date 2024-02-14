import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ResponseThread
 */
public class ClientRespondThread extends Thread {

    Socket server_socket = null;
    String server_address;

    public ClientRespondThread(Socket server_socket) {
        this.server_socket = server_socket;
        this.server_address = server_socket.getRemoteSocketAddress().toString();
    }

    public void run() {

        try {
            // Client Input : takes input from client
            BufferedReader client_input = new BufferedReader(new InputStreamReader(System.in));
            // Client Output : send response to client
            PrintWriter server_output = new PrintWriter(server_socket.getOutputStream(), true);

            String client_message;

            while (true) {
                
                if (server_socket.isClosed()) {
                    System.out.println("Notice : '" + server_address + "' disconnected...");
                    
                    client_input.close();
                    server_output.flush();
                    server_output.close();
                    server_socket.close();
                    return;
                }

                while ((client_message = client_input.readLine())!= null) {
                    // send server message to client
                    System.out.println(server_address+" :<-| " + client_message);
                    server_output.println(client_message);
                }
            }
        } catch (Exception e) {
            System.out.println("Notice : Server Connection Failed...");
            return;
        }
    }

}
