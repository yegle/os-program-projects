import java.util.ArrayList;

class SSTF implements DiskScheduler{
	private static int[] rString;
	private static int start;

	private static int sum;


	public SSTF(int[] rS, int s){
		this.rString = new int[rS.length];
		System.arraycopy(rS,0,rString,0,rS.length);
		this.start = s;
		this.sum=0;
	}

	public int serviceRequests(){
        int pos;
        int currentPos = this.start;
        int sum = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<this.rString.length; i++){
            list.add(new Integer(this.rString[i]));
        }
        for(int i=0; i<this.rString.length; i++){
            pos = this.findNearestPos(list, currentPos);
            sum += Math.abs(currentPos-pos);
            currentPos = pos;
        }
        return sum;
	}

    private int findNearestPos(ArrayList<Integer> list, int currentPos){
        int minDistance = Math.abs(currentPos-list.get(0));
        int distance;
        int pos = 0;
        int counter = 0;
        for(int i : list){
            distance = Math.abs(currentPos-i);
            if(distance<minDistance){
                minDistance = distance;
                pos = counter;
            }
            counter++;
        }
        list.remove(pos);
        return pos;
    }

	public static void main(String[] args){
        Generator ref = new Generator();

		int[] referenceString = ref.getCylinders();
		DiskScheduler sstf = new SSTF(referenceString, 13);

		System.out.println("SSTF = " + sstf.serviceRequests());
    }
}
