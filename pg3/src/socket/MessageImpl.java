import java.lang.Character;
import java.io.*;
import java.net.*;

/**
 * implementation of Message interface
 *
 * also implements the Serializable class to do serialization easier
 */
public class MessageImpl implements Message, Serializable {
    /**
     * holds the String representation of this instance
     */
    protected String input;
    /**
     * holds the number of character in this instance
     */
    protected int CharacterCount;
    /**
     * holds the number of digit in this instance
     */
    protected int DigitCount;

	//private Object lock;

    /**
     * constructor
     *
     * @param input String literal message
     */
    public MessageImpl(String input){
        this.input = input;
    }



    /**
     * setter for DigitCount and CharacterCount
     */
    public void setCounts(){
		long start = System.nanoTime();
        int dc= 0;
        for (int i=0; i< this.input.length(); i++){
            char c = this.input.charAt(i);
            if(Character.isDigit(c)){
				dc++;
            }
        }

        this.DigitCount = dc;
        this.CharacterCount = this.input.length();
		//Thread.sleep(3000);
		long end = System.nanoTime();
		System.err.println("finished request " + input + " cost:"+(end-start)+"ns");
    }

    public void singlesetCounts(Object lock){
		//lock = new Object();
		long start = System.nanoTime();
			synchronized(lock){
				int sdc= 0;
				for (int i=0; i< this.input.length(); i++){
				    char c = this.input.charAt(i);
				    if(Character.isDigit(c)){
						sdc++;
				    }
				}
        
				this.DigitCount = sdc;
				this.CharacterCount = this.input.length();
				//Thread.sleep(3000);
			}
		long end = System.nanoTime();
		System.err.println("finished request " + input + " cost:"+(end-start)+"ns");
    }
    /**
     * getter for CharacterCount
     *
     * @return number of characters in this instance
     */
    public int getCharacterCount(){
        return this.CharacterCount;
    }

    /**
     * getter for DigitCount
     *
     * @return number of digits in this instance
     */
    public int getDigitCount(){
        return this.DigitCount;
    }

    /**
     * simple class test function
     */
	public static void main(String[] args){
        try{
            String str = "asd023cjm-293ir098[zxdcv";
            MessageImpl msg = new MessageImpl(str);
            System.err.println("Testing string:" + str);
            System.out.println("getCharacterCount()=" + msg.getCharacterCount());
            System.out.println("getDigitCount()=" + msg.getDigitCount());
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
	}
}
