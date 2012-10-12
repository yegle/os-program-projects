import java.util.concurrent.Semaphore;

public class Global{
	public static int size=9000000;
	public static int totalsum=0;
	public static int[] a = new int[size];
	public static int[] b = new int[size];
	public static int[] c = new int[size];
	public static int[] sum = new int[10];
    public static Semaphore[] permitToEnterPhase2;
    public static Semaphore permitToEnterPhase3;
}
