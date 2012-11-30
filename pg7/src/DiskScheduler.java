public interface DiskScheduler
{
	// service the requests
	// return the amount of head movement
	// for the particular algorithm
	public int serviceRequests();
}
