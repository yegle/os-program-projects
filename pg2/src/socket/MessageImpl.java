import java.lang.Character;
import java.io.*;
import java.net.*;

public class MessageImpl implements Message, Serializable {
    protected String input;
    protected int CharacterCount;
    protected int DigitCount;

    public MessageImpl(String input){
        this.input = input;

        this.setCounts();
    }

    public void setCounts(){
        this.CharacterCount = this.getCharacterCount();
        this.DigitCount = this.getDigitCount();
        return;
    }

    public int getCharacterCount(){
        return this.input.length();
    }

    public int getDigitCount(){
        int ret = 0;
        for (int i=0; i< this.input.length(); i++){
            char c = this.input.charAt(i);
            if(Character.isDigit(c)){
                ret++;
            }
        }

        return ret;
    }

    public String serialize() throws Exception {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bs);
        out.writeObject(this.input);

        return URLEncoder.encode(bs.toString(),"utf-8");
    }

    public static MessageImpl normalize(String str) throws Exception {
        try{
            String originalString = URLDecoder.decode(str, "utf-8");
            ByteArrayInputStream bi = new ByteArrayInputStream(originalString.getBytes());
            ObjectInputStream in = new ObjectInputStream(bi);
            return (MessageImpl) in.readObject();
        }
        catch(Exception e){
            System.err.println("test");
            System.err.println( "Exception catched, message: " + e.toString());
            return new MessageImpl("");
        }
    }

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
