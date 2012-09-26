import java.rmi.*;

public interface Server extends Remote {
    public void setInput(String input) throws Exception;
    public void setCounts(String s) throws Exception;
    public int getCharacterCount() throws Exception;
    public int getDigitCount() throws Exception;
    public void setSingleThread(boolean b) throws Exception;
    public void debug(String s) throws Exception;
}

