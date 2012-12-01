class test{
	public static void main(String[] args){
        //Generator ref = new Generator();

		//int[] referenceString = ref.getCylinders();
		int[] referenceString = {98,183,37,122,14,124,65,67};
		DiskScheduler fcfs = new FCFS(referenceString, 53);
		System.out.println("FCFS = " + fcfs.serviceRequests());
        DiskScheduler sstf = new SSTF(referenceString, 53);
		System.out.println("SSTF = " + sstf.serviceRequests());
        DiskScheduler scan = new SCAN(referenceString, 53);
		System.out.println("SCAN = " + scan.serviceRequests());
        DiskScheduler cscan = new CSCAN(referenceString, 53);
		System.out.println("CSCAN = " + cscan.serviceRequests());
        DiskScheduler look = new LOOK(referenceString, 53);
		System.out.println("LOOK = " + look.serviceRequests());
    }
}
