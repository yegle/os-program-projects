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

    public String combine(String[] s, String glue){
        int k = s.length;
        if(k==0){
            return null;
        }

        StringBuilder ret = new StringBuilder();
        ret.append(s[0]);

        for(int i=1; i<k; i++){
            ret.append(glue).append(s[x]);
        }
        return ret.toString();
    }
}
