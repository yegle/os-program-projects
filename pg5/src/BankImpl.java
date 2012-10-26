import java.util.Hashtable;
import java.util.Arrays;

class BankImpl implements Bank{
	private static int numberOfCustomers = 0;
	private static int numberOfResources;

	private static int[] available;
	private static int[][] max;
	private static int[][] allocation;

	private static int[][] need;

    Hashtable customers = new Hashtable();

	public BankImpl(int[] resources){
		this.numberOfResources = resources.length;

		this.available = new int[numberOfResources];
		System.arraycopy(resources,0,available,0,numberOfResources);
	}

	public void addCustomer(int customerNumber, int[] maximumDemand){
        assert maximumDemand.length == this.numberOfResources;
        assert this.customers.containsKey(customerNumber) == false;

        if(this.numberOfCustomers == 0){
            //initialize arrays
            this.max = new int[1][this.numberOfResources];
            this.allocation = new int[1][this.numberOfResources];
            this.need = new int[1][this.numberOfResources];

            this.max[0] = maximumDemand;
        }
        else{
            //increase current array size
            this.max = Arrays.copyOf(this.max, this.max.length+1);
            this.allocation = Arrays.copyOf(this.allocation, this.allocation.length +1);
            this.need = Arrays.copyOf(this.need, this.need.length +1);

            this.max[this.max.length -1] = maximumDemand;
        }
        this.numberOfCustomers ++;
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
        return true;
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
