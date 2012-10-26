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
            for(int i=0;i<this.need.length;i++){
                this.need[0][i] = 0;
            }
        }
        else{
            //increase current array size
            this.maximum = Arrays.copyOf(this.maximum, this.maximum.length+1);
            this.allocation = Arrays.copyOf(this.allocation, this.allocation.length +1);
            this.need = Arrays.copyOf(this.need, this.need.length +1);

            this.maximum[this.maximum.length -1] = maximumDemand;

            this.allocation[this.allocation.length-1] = new int[this.allocation[0].length];
            for(int i=0; i<this.allocation[0].length; i++){
                this.allocation[this.allocation.length-1][i] = 0;
            }

            this.need[this.need.length-1] = new int[this.need[0].length];
            for(int i=0;i<this.need[0].length; i++){
                this.need[this.need.length-1][i] = 0;
            }
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
        int customerIndex = (Integer)this.customers.get(customerNumber);
		//if it is safe
		for(int i=0;i<resourceNumber;i++){
			available[i] -= request[i];
            System.err.println(customerIndex);
			allocation[customerIndex][i] += request[i];
			need[customerIndex][i] = maximum[customerIndex][i] - allocation[customerIndex][i];
		}
        return true;
	}

	public void releaseResources(int customerNumber, int[] release){
		System.out.println("Customer: "+ customerNumber +"releasing");
        int customerIndex = (Integer)this.customers.get(customerNumber);

		for(int i=0; i<resourceNumber;i++){
			available[i] +=release[i];
			allocation[customerIndex][i] -=release[i];
			need[customerIndex][i] = maximum[customerIndex][i] + allocation[customerIndex][i];
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

    protected boolean isSafe(int customerNumber, int[] request){
        int[] availableLocal = new int[this.resourceNumber];
        int[][] allocationLocal = new int[this.customerNumber][this.resourceNumber];
        int[][] needLocal = new int[this.customerNumber][this.resourceNumber];

        for(int i=0; i<this.available.length-1; i++){
            availableLocal[i] = this.available[i];
        }

        matrixCopy(this.allocation, allocationLocal);
        matrixCopy(this.need, needLocal);

        int customerIndex = (Integer)this.customers.get(customerNumber);

        for(int i=0; i<this.resourceNumber; i++){
            availableLocal[i] -= request[i];
            allocationLocal[customerIndex][i] += request[i];
            needLocal[customerIndex][i] -= request[i];
        }

        int[] work = availableLocal;
        boolean[] finish = new boolean[this.customerNumber];
        for(int i=0; i< finish.length; i++){
            finish[i] = false;
        }

        //test if it's safe
        for(int i=0; i< finish.length; i++){
            try{
                for(int j=0;j<work.length;j++){
                    assert needLocal[i][j] <= work[j];
                    work[j] += allocationLocal[i][j];
                }
            }
            catch(AssertionError e){
                return false;
            }

            finish[i] = true;
        }
        return true;
    }

    protected int[][] matrixOp(int[][] a, int[][] b, boolean plus){
        assert a.length == b.length;
        assert a[0].length == b[0].length;

        int[][] result = new int[a.length][a[0].length];

        for(int x=0; x< a.length; x++){
            for(int y=0; y<a[0].length; y++){
                if(plus){
                    result[x][y] = a[x][y] + b[x][y];
                }
                else{
                    result[x][y] = a[x][y] - b[x][y];
                }
            }
        }
        return result;
    }

    protected void matrixCopy(int[][] src, int[][] dest){
        for(int x=0; x< src.length; x++){
            for(int y=0; y<src[0].length; y++){
                dest[x][y] = src[x][y];
            }
        }
    }

    public static void main(String[] args){
        int[] available = {10,5,7};
        Bank bank = new BankImpl(available);
        int[] maximumDemand = {1,2,3};
        bank.addCustomer(0, maximumDemand);
        bank.requestResources(0, maximumDemand);
        bank.getState();
    }
}
