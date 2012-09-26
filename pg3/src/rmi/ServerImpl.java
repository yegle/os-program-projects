import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
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
     * internal variable to indicate if server should act as single threaded
     */
    protected boolean SingleThread;

    /**
     * internal variable to make ServerImpl single-threaded
     */
    private Object lock;

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
     * wrapper function 
     */
    public void setCounts() throws Exception {
        if(this.SingleThread){
            synchronized(this.lock){
                this._setCounts();
            }
        }
        else{
            this._setCounts();
        }
    }

    /**
     * function to do the real computing
     */
    public void _setCounts() throws Exception {
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
     * setter for SingleThread
     */

    public void setSingleThread(boolean b) throws Exception {
        this.lock = new Object();
        this.SingleThread = b;
        return;
    }

    /**
     * main function to run server
     */
    public static void main(String[] args) {
        try {
            ServerImpl s = new ServerImpl();
            
            if(args.length == 2 && args[1] == "single"){
                s.setSingleThread(true);
            }
            Registry registry = LocateRegistry.getRegistry(1988);

            //Naming.rebind("RMI", s);
            registry.bind("RMI", s);
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
    }
}
