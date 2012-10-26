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

        System.out.println("[" + this.combine(this.available, " ") + "]");

		System.out.println("Allocation:");

        System.out.println("[");
        for(int i=0; i<this.allocation.length; i++){
            System.out.println(this.combine(this.allocation[i], " "));
        }
        System.out.println("]");


		System.out.println("Maximum:");
        System.out.println("[");
        for(int i=0; i<this.max.length; i++){
            System.out.println(this.combine(this.max[i], " "));
        }
        System.out.println("]");

		System.out.println("Need:");
        System.out.println("[");
        for(int i=0; i<this.need.length; i++){
            System.out.println(this.combine(this.need[i], " "));
        }
	}

	public boolean requestResources(int customerNumber, int[] request){

	}

	public void releaseResources(int customerNumber, int[] release){

	}

    public String combine(int[] s, String glue){
        int k = s.length;
        if(k==0){
            return null;
        }

        StringBuilder ret = new StringBuilder();
        ret.append(s[0]);

        for(int i=1; i<k; i++){
            ret.append(glue).append(s[i]);
        }
        return ret.toString();
    }
}
