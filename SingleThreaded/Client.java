import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
//import java.net.UnknownHostException;

public class Client {

    public void run() {
        int port = 8010;  // Port where server is expected to be listening

        try {
            // Get localhost IP address
            InetAddress address = InetAddress.getByName("localhost"); // ❌ Fixed typo: "locaalhost" → ✅ "localhost"

            // Create a socket and connect to server at localhost:8010
            Socket socket = new Socket(address, port);

            // Create a PrintWriter to send data to the server
            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true); // ❌ Fixed: getOutStream() → ✅ getOutputStream(), added `true` to auto-flush

            // Create a BufferedReader to receive data from the server
            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a message to the server
            toSocket.println("Hello from the client");

            // Read a line of response from the server
            String line = fromSocket.readLine();

            // Print the server's response
            System.out.println("Response from the socket is: " + line);

            // Close the socket
            socket.close();

        } catch (IOException e) {
            // Handle exceptions related to networking
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create and run the client
        Client client = new Client();
        client.run();
    }
}
