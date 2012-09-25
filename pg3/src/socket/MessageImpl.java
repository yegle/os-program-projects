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
        int dc= 0;
        for (int i=0; i< this.input.length(); i++){
            char c = this.input.charAt(i);
            if(Character.isDigit(c)){
		dc++;
            }
        }

        this.DigitCount = dc;
        this.CharacterCount = this.input.length();
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
