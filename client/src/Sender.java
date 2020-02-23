import java.io.*;
import java.util.*;

public class Sender extends Thread{
    Scanner scn;
    DataOutputStream dos;

    public Sender(Scanner scn, DataOutputStream dos) {
        this.scn = scn;
        this.dos = dos;
    }

    @Override
    public void run() {
        while (true) {

            // read the message to deliver.
            String msg = scn.nextLine();

            try {
                // write on the output stream
                dos.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
