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
}
