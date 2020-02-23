import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private String name;
    private Socket s;
    private boolean isLoggedIn;

    // constructor
    public ClientHandler(Socket s,
                         DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.s = s;
        this.isLoggedIn = true;
    }

    @Override
    public void run() {

        String received;
        while (true) {
            try {
                // receive the string
                received = dis.readUTF();

                if (received.contains("as:")) {

                    this.name = received.split(":")[1];

                    if (Server.registry.get(this.name) == null) {

                        System.out.println(this.name + " is connected.");
                        Server.registry.put(this.name, this);
                        this.dos.writeUTF(this.name + " is Logged In.");

                    } else {

                        System.out.println("This name is already exist.");
                        this.dos.writeUTF(this.name + " is already exist.");
                        s.close();
                        break;
                    }

                } else if (received.equalsIgnoreCase("list")) {

                    Server.registry.forEach((k, v) -> {
                        try {
                            this.dos.writeUTF(k);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else if (received.contains("->")) {

                    // break the string into message and recipient part
                    StringTokenizer st = new StringTokenizer(received, "->");
                    String MsgToSend = st.nextToken().trim();
                    String recipient = st.nextToken().trim();

                    // search for the recipient in the connected devices list.
                    ClientHandler mc = Server.registry.get(recipient);

                    // if the recipient is found, write on its
                    // output stream
                    if (mc != null && mc.isLoggedIn) {

                        mc.dos.writeUTF(this.name + " : " + MsgToSend);

                    } else {

                        this.dos.writeUTF(recipient + " is not registered");
                    }

                    this.dos.writeUTF("");
                } else {
                    this.dos.writeUTF("Nothing happened from the input " + received);
                }
            } catch (IOException e) {

                e.printStackTrace();
                break;
            }

        }

    }
}