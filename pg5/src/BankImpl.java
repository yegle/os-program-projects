class BankImpl implements Bank{
	private static int numberOfCustomers;
	private static int numberOfResources;

	private static int[] available;
	private static int[][] maximum;
	private static int[][] allocation;

	private static int[][] need;

	public BankImpl(int[] resources){
		numberOfResources=resources.length;

		available = new int[numberOfResources];
		System.arraycopy(resources,0,available,0,numberOfResources);

		maximum = new int[numberOfCustomers][];
		allocation = new int [numberOfCustomers][];
		need = new int[numberOfCustomers][];
	}
<<<<<<< HEAD

	public void addCustomer(int customerNumber, int[] maximumDemand){

	}

	public void getState(){
		System.out.println("Available:");
		System.out.print("[ ");
		for(int i=0;i<numberofResources;i++){
			System.out.print(available[i] + " ");
		}
		System.out.println("]");

		System.out.println("Allocation:");
		for(i=0;i<numberofCustomers;i++){
			System.out.print("[ ");
			for(int j=0;j<numberofResources;j++){
				System.out.print(allocation[i][j] + " ");
			}
			System.out.println("]");
		}	

		System.out.println("Maximum:");
		for(i=0;i<numberofCustomers;i++){
			System.out.print("[ ");
			for(int j=0;j<numberofResources;j++){
				System.out.print(allocation[i][j] + " ");
			}
			System.out.println("]");
		}

		System.out.println("Need:");
		for(i=0;i<numberofCustomers;i++){
			System.out.print("[ ");
			for(int j=0;j<numberofResources;j++){
				System.out.print(allocation[i][j] + " ");
			}
			System.out.println("]");
		}	
	}

	public boolean requestResources(int customerNumber, int[] request){

	}

	public void releaseResources(int customerNumber, int[] release){

	}
=======
    public void releaseResources(int customerNumber, int[] release){
    }
    public void addCustomer(int customerNumber, int[] maximumDemand){
    }
    public void getState(){
    }
    public boolean requestResources(int customerNumber, int[] request){
        return true;
    }
>>>>>>> 8e71d3a9e9a7ea29a8079ac7aef7e4e57420a1de
}
