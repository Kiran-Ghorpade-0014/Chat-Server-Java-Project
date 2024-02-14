import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class Server {

    public static void main(String[] args){

            ServerSocket server_socket = null;
            Socket client_socket = null;

            try {
            server_socket = new ServerSocket(888);
            System.out.println("Notice : Server Started at port : "+ server_socket.getLocalPort());
            // System.out.println("----````````````````````````````````````----");
            // System.out.println("---- Enter Shift+` to Broadcast Message ----");
            // System.out.println("----,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,----");

            while (true) {

                client_socket = server_socket.accept();
                
                if (client_socket.isClosed()) {
                    System.out.println("Notice : A Client Failed To Connect...");
                }
                
                System.out.println("Notice : New User Connected : "+ client_socket.getRemoteSocketAddress());
                new SocketThread(client_socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
