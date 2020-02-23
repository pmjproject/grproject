import java.io.*;

public class Reader extends Thread {
    DataInputStream dis;
    String name;

    public Reader(DataInputStream dis, String name) {
        this.dis = dis;
        this.name = name;
    }

    @Override
    public void run() {

        while (true) {
            try {
                // read the message sent to this client
                String msg = dis.readUTF();
                System.out.println(msg);
                if(msg.equalsIgnoreCase(this.name + " is already exist.")){
                    System.exit(1);
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
