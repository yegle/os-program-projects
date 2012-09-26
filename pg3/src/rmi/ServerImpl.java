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
     * debug function, to stderr
     */
    public void debug(String s) throws Exception{
        System.err.println(s);
    }

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
    public void setCounts(String s) throws Exception {
        long start = System.nanoTime();
        if(this.SingleThread){
            synchronized(this.lock){
                this._setCounts();
            }
        }
        else{
            this._setCounts();
        }
        long end = System.nanoTime();
        System.err.println("finished request " + s + " cost: " + (end - start) + " ms");
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
        Thread.sleep(3000);
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
        System.err.println("setSingleThread");
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
            
            if(args.length == 1 && args[0].equals("single") ){
                System.err.println("Running in single-thread mode");
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
