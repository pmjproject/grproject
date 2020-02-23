import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private static final String TRUSTSTORE_LOCATION = "/home/pmj/Documents/SSL/CA/ClientKeyStore.jks";
    private static final String TRUSTSTORE_PASSWORD = "pmj@123";

    public static void main(String[] a) throws IOException {

        System.setProperty("javax.net.ssl.trustStore", TRUSTSTORE_LOCATION);
        System.setProperty("javax.net.ssl.trustStorePassword", TRUSTSTORE_PASSWORD);

        System.out.println("Client will start");
        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();

        String[] args = input.trim().split(" ");

        if (args.length != 4) {
            System.out.println("Invalid Input : " + input);
            main(a);
        }

        String command = args[0];
        String socket = args[1];
        String action = args[2];
        String client = args[3];

        if (!command.equalsIgnoreCase("connect")) {
            System.out.println("Invalid command : " + command);
            main(a);
        }

        String host = socket.split(":")[0];

        int port = 0;
        try {
            port = Integer.valueOf(socket.split(":")[1]);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Invalid Port : " + socket.split(":")[1]);
            main(a);
        }
        // getting localhost ip
        InetAddress ip = InetAddress.getByName(host);
        SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket c = (SSLSocket) f.createSocket(ip, port);

        // establish the connection
        c.startHandshake();

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(c.getInputStream());
        DataOutputStream dos = new DataOutputStream(c.getOutputStream());

        //Register Client
        dos.writeUTF(action + ":" + client);
        // sendMessage thread
        Thread sendMessage = new Sender(scn, dos);

        // readMessage thread
        Thread readMessage = new Reader(dis, client);

//        registerClient.start();
        sendMessage.start();
        readMessage.start();
    }
}