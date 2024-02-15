import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ResponseThread
 */
public class ClientRespondThread extends Thread {

    Socket server_socket = null;
    String server_address;
    Console client_input;
    PrintWriter server_output;
    BufferedReader server_input;

    public ClientRespondThread(Socket server_socket) throws IOException {
        this.server_socket = server_socket;
        this.server_address = server_socket.getRemoteSocketAddress().toString();

        // Client Output : send response to client
        this.server_output = new PrintWriter(server_socket.getOutputStream(), true);
        //  Server Input : receive request from server
        this.server_input = new BufferedReader(new InputStreamReader(this.server_socket.getInputStream()));
        // Client Input from console
        this.client_input = System.console();
    }

    static public String encrypt(String message){
        String encrypted_message="";
        for (int i=0;i<message.length();i++){
            int ascii_val = message.codePointAt(i)+5;
            char ch = (char)ascii_val;
            encrypted_message +=ch;
        }
        return encrypted_message;
    }
    static public String decrypt(String message){
        String decrypted_message= "";
        for (int i=0;i<message.length();i++){
            int ascii_val = message.codePointAt(i)-5;
            if (ascii_val <32){
                ascii_val+=96;   //ASCII value of 'a' is 97 so we add 96 to get back to the range
            }
            char ch = (char)ascii_val;
            decrypted_message +=ch;
        }
        return decrypted_message;
    }

    public void run() {

        try {

            String client_message = "";
            String server_message = "";

            do {
                server_message= server_input.readLine();   // read message from the server
                System.out.println(decrypt(server_message));

                client_message = client_input.readLine("Your Message :");
                // send server message to client
                // System.out.println(server_address + " :|<-| " + client_message);
                server_output.println(encrypt(client_message));
                // this.wait(1000);

            } while (!(client_message.equals("exit")));

        } catch (Exception e) {
            System.out.println("Notice : Server Connection Failed 1...");
            // return;
        }
    }

}
