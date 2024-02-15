
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client
 */
public class Client {
    public static void main(String[] args){

        try {
            Socket server_socket = new Socket("localhost", 888);

            // new ClientListenThread(server_socket);
            new ClientRespondThread(server_socket).start();

            while (true) {
                if (server_socket.isClosed()) {
                    server_socket.close();
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Error : Server Not Found...");
        } catch (IOException e) {
            System.out.println("Error : Server Connection Failed...");
        }
    }
}
