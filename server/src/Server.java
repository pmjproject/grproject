import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int HTTPS_PORT = 8282;
    private static final String KEYSTORE_LOCATION = "/home/PMJ/Documents/SSL/Key/ServerKeyStore.jks";
    private static final String KEYSTORE_PASSWORD = "pmj@123";

    static ConcurrentHashMap<String, ClientHandler> registry = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);

        ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(HTTPS_PORT);
        System.out.println("Chat Server Started on socket:127.0.0.1:" + HTTPS_PORT );

        Socket s;

        // running infinite loop for getting client request
        while (true) {

            // Accept the incoming request
            s = serverSocket.accept();

            // obtain input and output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // Create a new handler object for handling this request.
            ClientHandler clientHandler = new ClientHandler(s, dis, dos);

            // Create a new Thread with this object.
            Thread t = new Thread(clientHandler);

            // add this client to active clients list
            t.start();
        }
    }
}

