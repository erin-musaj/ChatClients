import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MsgReceiver implements Runnable {

    Socket ss;

    public MsgReceiver(Socket ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            while(true) {
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
