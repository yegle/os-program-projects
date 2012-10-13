import java.util.concurrent.Semaphore;

/**
 * class Global
 */

public class Global{
    public static int size=9000000;
    public static int totalsum=0;
    public static int[] a = new int[size];
    public static int[] b = new int[size];
    public static int[] c = new int[size];
    public static long[] sum;
    public static BarrierImpl EnteringPhase2;
    public static BarrierImpl EnteringPhase3;
}
