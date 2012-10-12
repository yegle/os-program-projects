//import java.util.concurrent.Semaphore;

public class Dot{
	//private static int[] sum = new int[10];


	public static void main(String[] args){
		//read data from files and assign values to a,b
		for(int j=0;j<Global.size;j++){
			Global.a[j]=j;
			Global.b[j]=j;
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




	/*static class adder implements Runnable{
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
	}*/

}
