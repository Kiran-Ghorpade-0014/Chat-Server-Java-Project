
import java.net.Socket;

/**
 * SocketThread
 */
public class SocketThread extends Thread {

    Socket client_socket = null;
    String client_address;

    public SocketThread(Socket client_socket) {
        this.client_socket = client_socket;
        this.client_address = client_socket.getInetAddress().getHostAddress();
    }

    public void run() {

        try {
            System.out.println("listening...");

            if (client_socket.isClosed()) {
                System.out.println("Notice : '" + client_address + "' disconnected...");

                client_socket.close();
                return;
            }

            // System.out.println("listening started");
            new ServerListenThread(client_socket).start();
            // new ServerRespondThread(client_socket).start();

        } catch (Exception e) {
            System.out.println("Notice : '" + client_address + "' disconnected...");
            return;
        }
    }
}
