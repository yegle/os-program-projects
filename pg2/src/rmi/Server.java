import java.rmi.*;

public interface Server extends Remote {
    public void setInput(String input) throws Exception;
    public void setCounts() throws Exception;
    public int getCharacterCount() throws Exception;
    public int getDigitCount() throws Exception;
}

