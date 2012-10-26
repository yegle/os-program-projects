class BankImpl implements Bank{
	private static int customerNumber;
	private static int resourceNumber;

	private static int[] available;
	private static int[][] maximum;
	private static int[][] allocation;

	private static int[][] need;

	public BankImpl(int[] resources){
		resourceNumber=resources.length;

		available = new int[resourceNumber];
		System.arraycopy(resources,0,available,0,resourceNumber);

		maximum = new int[customerNumber][];
		allocation = new int [customerNumber][];
		need = new int[customerNumber][];
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
		System.out.println("Customer: "+ customerNumber +"releasing");

		for(int i=0; i<resourceNumber;i++){
			available[i] +=release[i];
			allocation[customerNumber][i] -=release[i];
			need[customerNumber][i] = maximum[customerNumber][i] + allocation[customerNumber][i];
		}
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
