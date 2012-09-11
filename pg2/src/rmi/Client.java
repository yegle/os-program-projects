public class RMIServer {
 public static void registRemoteObject() throws Exception { 
  IRemoteImp impl = new IRemoteImp(); 
        Naming.rebind("rmi://127.0.0.1:1111/mytask", impl); 
        System.out.println("bound success!"); 
    } 

    /** 
     * 创建一个Registry对象 
     * 
     * @return 返回一个Registry对象 
     */ 
    private static Registry createRegistry() { 
        Registry registry = null; 
        int port = 1111; 
        try { 
            registry = LocateRegistry.getRegistry(port); 
            registry.list(); 
            System.out.println("Register the exist server!"); 
        } catch (final Exception e) { 
            try { 
                registry = LocateRegistry.createRegistry(port); 
                System.out.println("Register the exist server!port=" + port); 
            } catch (final Exception ee) { 
                ee.printStackTrace(); 
            } 
        } 
        return registry; 
    } 

    /** 
     * 将对象注册到rmi服务器上 
     */ 
    public static void bind() { 
        Registry registry = null; 
        registry = createRegistry(); 
        try { 
         IRemoteImp impl = new IRemoteImp(); 
            registry.rebind("mytask", impl); 
            System.out.println("mytask server start!"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 

    /** 
     * @param args 
     */ 
    public static void main(String[] args) { 
        try { 
            bind(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 

}
