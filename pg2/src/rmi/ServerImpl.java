import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

/**
 * Main server class
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
    /**
     * internal variable used to save user's input
     */
    protected String Input;
    /**
     * internal variable used to save result
     */
    protected int CharacterCount, DigitCount;

    /**
     * empty constructor
     */
    public ServerImpl() throws Exception{}

    /**
     * function used to save user's input
     *
     * @param input String, User's input
     */
    public void setInput(String input) throws Exception {
        this.Input = input;
    }

    /**
     * function to do the real computing
     */
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

    /**
     * getter for DigitCount
     *
     * @return this.DigitCount
     */
    public int getDigitCount() throws Exception {
        return this.DigitCount;
    }

    /**
     * getter for CharacterCount
     *
     * @return this.CharacterCount
     */
    public int getCharacterCount() throws Exception {
        return this.CharacterCount;
    }

    /**
     * main function to run server
     */
    public static void main(String[] args) {
        try {
            Server s = new ServerImpl();

            Naming.rebind("RMI", s);
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
    }
}
