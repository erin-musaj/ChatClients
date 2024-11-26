import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket cs;

    public ClientHandler(Socket cs) {
        this.cs = cs;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            Server.css.add(out);
            String msg = "";
            while((msg = in.readLine()) != null){
                System.out.println("Messaggio da " + cs + ": " + msg);
                for(int i = 0; i < Server.css.size(); i++){
                    if(Server.css.get(i) != out) {
                        Server.css.get(i).writeBytes(msg + "\n");
                        out.flush();
                    }
                }
            }

            in.close();
            Server.css.remove(out);
            out.close();
            System.out.println("Client " + cs + " si è disconnesso.");
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
