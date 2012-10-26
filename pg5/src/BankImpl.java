import java.util.Hashtable;
import java.util.Arrays;

class BankImpl implements Bank{
	private static int customerNumber;
	private static int resourceNumber;

	private static int[] available;
	private static int[][] maximum;
	private static int[][] allocation;

	private static int[][] need;

    Hashtable customers = new Hashtable();

	public BankImpl(int[] resources){
		this.resourceNumber = resources.length;

		this.available = new int[resourceNumber];
		System.arraycopy(resources,0,available,0,resourceNumber);
        this.customerNumber = 0;
	}

	public void addCustomer(int customerNumber, int[] maximumDemand){
        assert maximumDemand.length == this.resourceNumber;
        assert this.customers.containsKey(customerNumber) == false;

        if(this.customerNumber == 0){
            //initialize arrays
            this.maximum = new int[1][this.resourceNumber];
            this.allocation = new int[1][this.resourceNumber];
            this.need = new int[1][this.resourceNumber];

            this.maximum[0] = maximumDemand;
        }
        else{
            //increase current array size
            this.maximum = Arrays.copyOf(this.maximum, this.maximum.length+1);
            this.allocation = Arrays.copyOf(this.allocation, this.allocation.length +1);
            this.need = Arrays.copyOf(this.need, this.need.length +1);

            this.maximum[this.maximum.length -1] = maximumDemand;
        }
        this.customers.put(customerNumber, new Integer(this.maximum.length-1));
        this.customerNumber ++;
	}

	public void getState(){
		System.out.println("Available:");
        System.out.println("[" + this.combine(this.available, " ") + "]");

		System.out.println("Allocation [");
        for(int i=0; i<this.allocation.length; i++){
            System.out.println(this.combine(this.allocation[i], " "));
        }
        System.out.println("]");


		System.out.println("Maximum [");
        for(int i=0; i<this.maximum.length; i++){
            System.out.println(this.combine(this.maximum[i], " "));
        }
        System.out.println("]");

		System.out.println("Need [");
        for(int i=0; i<this.need.length; i++){
            System.out.println(this.combine(this.need[i], " "));
        }
        System.out.println("]");
	}

	public boolean requestResources(int customerNumber, int[] request){
        return true;
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

    public static void main(String[] args){
        int[] available = {10,5,7};
        Bank bank = new BankImpl(available);
        int[] maximumDemand = {1,2,3};
        bank.addCustomer(0, maximumDemand);
        bank.getState();
    }
}
