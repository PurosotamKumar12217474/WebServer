import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server {

    // This method starts the server and waits for client connections
    public void run() throws IOException {
        int port = 8010;  // Port number for the server
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started. Waiting for client on port " + port + "...");

        while (true) {
            try {
                // Wait for client connection (blocking call)
                Socket acceptedConnection = serverSocket.accept();
                System.out.println("Connection accepted from client: " + acceptedConnection.getRemoteSocketAddress());

                // Set up output stream to send data to client (auto-flush enabled)
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);

                // Set up input stream to read data from client
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                // Send a message to the client
                toClient.println("Hello from the server");

                // Optional: Read message from client
                String clientMessage = fromClient.readLine();
                System.out.println("Message from client: " + clientMessage);

                // Close connection with this client
                acceptedConnection.close();

            } catch (IOException ex) {
                // Handle I/O errors
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();  // Create an instance of the Server class
        try {
            server.run();  // Start the server
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
