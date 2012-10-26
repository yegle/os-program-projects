import java.util.Hashtable;
import java.util.Arrays;
import java.lang.Exception;

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
        try{
            if(maximumDemand.length != this.resourceNumber){
                throw new Exception("wrong resource number");
            }
            if(this.customers.containsKey(customerNumber)){
                throw new Exception("this customer is already added?");
            }
        }
        catch(Exception e){
            System.err.println("Exception caught. Exit");
            System.exit(-1);
        }
        //assert maximumDemand.length == this.resourceNumber;
        //assert this.customers.containsKey(customerNumber) == false;

        //System.err.println(customerNumber + ", " + this.combine(maximumDemand, "|"));

        if(this.customerNumber == 0){
            //initialize arrays
            this.maximum = new int[1][this.resourceNumber];
            this.allocation = new int[1][this.resourceNumber];
            this.need = new int[1][this.resourceNumber];

            for(int i=0;i<this.maximum[0].length;i++){
                this.maximum[0][i] = maximumDemand[i];
            }
            for(int i=0;i<this.need[0].length;i++){
                this.need[0][i] = this.maximum[0][i];
            }
        }
        else{
            //increase current array size
            this.maximum = Arrays.copyOf(this.maximum, this.maximum.length+1);
            this.allocation = Arrays.copyOf(this.allocation, this.allocation.length +1);
            this.need = Arrays.copyOf(this.need, this.need.length +1);

            this.maximum[this.maximum.length -1] = new int[this.maximum[0].length];
            for(int i=0;i<this.maximum[0].length;i++){
                this.maximum[this.maximum.length-1][i] = maximumDemand[i];
            }

            this.allocation[this.allocation.length-1] = new int[this.allocation[0].length];
            for(int i=0; i<this.allocation[0].length; i++){
                this.allocation[this.allocation.length-1][i] = 0;
            }

            this.need[this.need.length-1] = new int[this.need[0].length];
            for(int i=0;i<this.need[0].length; i++){
                this.need[this.need.length-1][i] = this.maximum[this.maximum.length-1][i];
            }
        }
        this.customers.put(customerNumber, new Integer(this.maximum.length-1));
        this.customerNumber ++;
        //System.err.println(customerNumber + ", "+ this.combine(this.maximum[this.maximum.length-1], "|"));
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
        try{
            for(int i=0;i<this.resourceNumber; i++){
                if(this.available[i] < request[i]){
                    throw new Exception();
                }
                if(request[i] + this.allocation[customerIndex][i] >= this.maximum[customerIndex][i]){
                    throw new Exception();
                }
                //assert (this.available[i] > request[i]);
                //assert (request[i] + this.allocation[customerIndex][i] < this.maximum[customerIndex][i]);
            }
        }
        catch(Exception e){
            return false;
        }
        if(!this.isSafe(customerNumber, request)){
            System.err.println("Not safe");
            return false;
        }
		//if it is safe
		for(int i=0;i<resourceNumber;i++){
			available[i] -= request[i];
            //System.err.println(customerIndex);
			allocation[customerIndex][i] += request[i];
			need[customerIndex][i] = maximum[customerIndex][i] - allocation[customerIndex][i];
		}
        return true;
	}

	public void releaseResources(int customerNumber, int[] release){
		System.out.println("Customer: "+ customerNumber +" releasing");
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

        for(int i=0; i<this.available.length; i++){
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


        //System.err.println("needLocal begin");
        //for(int i=0;i<this.customerNumber;i++){
        //    System.err.println(this.combine(needLocal[i], ", "));
        //}
        //System.err.println("needLocal end");

        int[] work = new int[availableLocal.length];
        for(int i=0; i<work.length;i++){
            work[i] = availableLocal[i];
        }
        //System.err.println("work begin");
        //System.err.println(this.combine(work, ", "));
        //System.err.println("work end");
        boolean[] finish = new boolean[this.customerNumber];
        for(int i=0; i< finish.length; i++){
            finish[i] = false;
        }

        //test if it's safe
        for(int i=0; i< finish.length; i++){
            try{
                for(int j=0;j<work.length;j++){
                    //System.err.println(needLocal[i][j] + ", " + work[j]);
                    if(needLocal[i][j] > work[j]){
                        throw new Exception();
                    }

                    //assert needLocal[i][j] <= work[j];
                    work[j] += allocationLocal[i][j];
                }
            }
            catch(Exception e){
                return false;
            }

            finish[i] = true;
        }
        return true;
    }

    protected int[][] matrixOp(int[][] a, int[][] b, boolean plus) throws Exception{
        if(a.length != b.length){
            throw new Exception("matrix size mismatch");
        }
        if(a[0].length != b[0].length){
            throw new Exception("matrix size mismatch");
        }
        //assert a.length == b.length;
        //assert a[0].length == b[0].length;

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
