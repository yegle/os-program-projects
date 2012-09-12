import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class ServerImpl extends UnicastRemoteObject implements Server {
    protected String Input;
    protected int CharacterCount, DigitCount;

    public ServerImpl() throws Exception{}

    public void setInput(String input) throws Exception {
        this.Input = input;
    }

    public void setCounts() throws Exception {
        int ret = 0;
        for (int i=0; i< this.Input.length(); i++){
            char c = this.Input.charAt(i);
            if(Character.isDigit(c)){
                ret++;
            }
        }

        this.DigitCount = ret;
        this.CharacterCount = this.Input.length();
    }

    public int getDigitCount() throws Exception {
        return this.DigitCount;
    }

    public int getCharacterCount() throws Exception {
        return this.CharacterCount;
    }

    public static void main(String[] args) {
        try {
            Server s = new ServerImpl();

            Naming.rebind("RMI", s);
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
    }
}
