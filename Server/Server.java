import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Server
 */
public class Server {

    static ServerSocket server_socket = null;
    static Socket client_socket = null;
    static Set<SocketThread> usersSockets = new HashSet<>();

    static void broadcast(String message, SocketThread exclude_client) throws IOException{
        for(SocketThread client : usersSockets) {
            if (client != exclude_client) {
                client.sendMessage(message);
            }
        }
    }

    public static void main(String[] args){
            try {
            server_socket = new ServerSocket(888);
            System.out.println("Notice : Server Started at port : "+ server_socket.getLocalPort());

            while (true) {

                client_socket = server_socket.accept();
                
                if (client_socket.isClosed()) {
                    System.out.println("Notice : A Client Failed To Connect...");
                }
                
                System.out.println("Notice : New User Connected : "+ client_socket.getRemoteSocketAddress());
                SocketThread new_client = new SocketThread(client_socket);
                usersSockets.add(new_client);
                new_client.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeClient(String client_address, SocketThread socketThread) {
        usersSockets.remove(socketThread);
        System.out.println("Notice : Disconnected from " + client_address);
    }
}