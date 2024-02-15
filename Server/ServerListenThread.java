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

        } catch (Exception e) {
            System.out.println("Notice : '" + client_address + "' disconnected...");
            return;
        }
    }
}