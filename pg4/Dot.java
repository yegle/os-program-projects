//import java.util.concurrent.Semaphore;


public class Dot{
	private static int totalsum=0;
	private static int size=10;

	private static int[] a = new int[size];
	private static int[] b = new int[size];
	private static int[] c = new int[size];
	//private static int[] sum = new int[10];


	public static void main(String[] args){
		
		//read data from files and assign values to a,b
		for(int j=0;j<size;j++){
			a[j]=j;
			b[j]=j;
		}

		//create thread to multiply
		int tsize = 10;
		Thread[] mul = new Thread[tsize];

		for(int j=0;j<tsize;j++){
			//System.err.println(j);
			mul[j]=new Thread(new Multiplier(j));

		}

		for(int j=0;j<tsize;j++){
			System.err.println(j);
			mul[j].start();
		}

		//create thread to add
		/*Thread[] add = new Thread[tsize];

		for(int j=0;j<tsize;j++){
			//System.err.println(j);
			add[j]=new Thread(new Multiplier(j));

		}

		for(int j=0;j<tsize;j++){
			System.err.println(j);
			add[j].start();
		}*/

	}



	static class Multiplier implements Runnable{
		private int i;
		Multiplier(int id){
			i=id;		
		}

		public void run(){
			try{		
				c[i]=a[i]*b[i];
				System.err.println("c[" + i + "]=" + c[i]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	static class adder implements Runnable{
		private int i;

		adder(int id){
			i=id;		
		}

		public void run(){
			try{		
				sum+=c[i];
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
