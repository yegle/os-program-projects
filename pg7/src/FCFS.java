class FCFS implements DiskScheduler{
	
	private static int[] rString;
	private static int start;

	private static int sum;


	public FCFS(int[] rS, int s){
		this.rString = new int[rS.length];
		System.arraycopy(rS,0,rString,0,rS.length);
		this.start = s;
		this.sum=0;
	}

	public int serviceRequests(){
		sum += Math.abs(rString[1] - start);
		for(int i=1;i<rString.length;i++){
			sum += Math.abs(rString[i]-rString[i-1]);
		}

		return sum;
	}

	public static void main(String[] args){
        Generator ref = new Generator();

		int[] referenceString = ref.getCylinders();
		DiskScheduler fcfs = new FCFS(referenceString, 13);

		System.out.println("FCFS = " + fcfs.serviceRequests());
    }
}
