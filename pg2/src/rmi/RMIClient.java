import java.rmi.*;

public class RMIClient {
 public static void getRemoteObject() throws Exception{ 
  IRemote obj = (IRemote)Naming.lookup("rmi://127.0.0.1:1111/mytask");
  Task task = new Task(); 
  task.doWork();
        Object result = obj.invoke(task);
       
    } 

    /** 
     * @param args 
     * @throws Exception 
     */ 
    public static void main(String[] args) throws Exception { 
     RMIClient.getRemoteObject();
    } 

}
