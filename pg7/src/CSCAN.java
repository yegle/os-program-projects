import java.util.ArrayList;

class CSCAN implements DiskScheduler{
	private static int[] rString;
	private static int start;

	private static int sum;


	public CSCAN(int[] rS, int s){
		this.rString = new int[rS.length];
		System.arraycopy(rS,0,rString,0,rS.length);
		this.start = s;
		this.sum=0;
	}

    public int serviceRequests(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<this.rString.length; i++){
            list.add(new Integer(this.rString[i]));
        }

        int direction = 1;
        int currentPos = this.start;
        int sum = 0;
        while(!list.isEmpty()){
            currentPos += direction;
            sum++;
            if(list.contains(new Integer(currentPos))){
                list.remove(new Integer(currentPos));
            }
            if(currentPos == 199){
                sum += 199;
                currentPos = 0;
            }
        }
        return sum;
    }

	public static void main(String[] args){
        //Generator ref = new Generator();

		//int[] referenceString = ref.getCylinders();
		int[] referenceString = {98,183,37,122,14,124,65,67};
		DiskScheduler scan = new FCFS(referenceString, 53);

		System.out.println("SCAN = " + scan.serviceRequests());
    }
}
