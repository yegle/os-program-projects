import java.rmi.*;
import java.io.*;

public class Client {
    protected Server server;
    protected String url;

    public Client(String url){
        this.url = url;
    }

    public static void main(String[] args){
        try{
            Client c = new Client("rmi://127.0.0.1/RMI");
            c.connect();

            System.err.println("Enter the string to be processed");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            c.process(input);

            c.printResult();
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
    }

    protected void connect() throws Exception {
        this.server = (Server) Naming.lookup(url);
    }

    protected void process(String input){
        this.server.setInput(input);
        this.server.setCounts();
    }

    protected void printResult(){
        System.out.println("Result received: CharacterCount=" + this.server.getCharacterCount() + ", DigitCount=" + this.server.getDigitCount());
    }
}
