
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client
 */
public class Client {
    public static void main(String[] args) {

        try {
            Socket server_socket = new Socket("localhost", 888);

            new ClientListenThread(server_socket);
            new ClientRespondThread(server_socket);

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
