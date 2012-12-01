import java.util.ArrayList;

class SCAN implements DiskScheduler{
	
	private static int[] rString;
	private static int start;

	private static int sum;


	public SCAN(int[] rS, int s){
		this.rString = new int[rS.length];
		System.arraycopy(rS,0,rString,0,rS.length);
		this.start = s;
		this.sum=0;
	}

	//public int serviceRequests(){
	//	int pos;
	//	int currentPos = this.start;
	//	int flag=1;
	//	int count=0;

	//	ArrayList <Integer> list = new ArrayList<Integer>();
	//	for(int i=0;i<this.rString.length;i++){
	//		list.add(new Integer(this.rString[i]));
	//	}
	//	while((pos=this.findNearestPos(list, currentPos))>=0){
	//		sum += Math.abs(currentPos-pos);
	//		currentPos = pos;
	//		count++;
	//	}

	//	sum += currentPos;
	//	currentPos = 0;
	//	for(int i=0;i<this.rString.length-count;i++){
	//		pos = this.findNearestPos2(list, currentPos);
    //        sum += Math.abs(currentPos-pos);
    //        currentPos = pos;
	//	}

	//	return sum;
	//}
    public int serviceRequests(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<this.rString.length; i++){
            list.add(new Integer(this.rString[i]));
        }

        int direction = -1;
        int currentPos = this.start;
        int lastPos = this.start;
        int sum = 0;
        while(!list.isEmpty()){
            currentPos += direction;
            sum++;
            if(list.contains(new Integer(currentPos))){
                list.remove(new Integer(currentPos));
            }
            if(currentPos == 0 || currentPos == 200){
                direction = -direction;
            }
        }
        return sum;
    }

    private int findNearestPos(ArrayList<Integer> list, int currentPos){
        int minDistance = Math.abs(currentPos-0);
        int distance;
        int pos = 0;
        int counter = 0;
		int flag = 0;
		int ret = 0;
        for(int i : list){
            distance = currentPos-i;
            if((distance>=0)&&(distance<minDistance)){
                minDistance = distance;
                pos = counter;
				flag = 1;
            }
            counter++;
        }
		if(flag==1){
			ret = list.get(pos);
        	list.remove(pos);
		}
		else
			ret=-1;

       	return ret;
    }

	private int findNearestPos2(ArrayList<Integer> list, int currentPos){
        int minDistance = Math.abs(currentPos-0);
        int distance;
        int pos = 0;
        int counter = 0;
		int ret = 0;
        for(int i : list){
            distance = Math.abs(currentPos-i);
            if((distance<minDistance)){
                minDistance = distance;
                pos = counter;
            }
            counter++;
        }
		ret = list.get(pos);
        list.remove(pos);
        return ret;

    }

	public static void main(String[] args){
        //Generator ref = new Generator();

		//int[] referenceString = ref.getCylinders();
		int[] referenceString = {98,183,37,122,14,124,65,67};
		DiskScheduler scan = new FCFS(referenceString, 53);

		System.out.println("SCAN = " + scan.serviceRequests());
    }
}
