package zadacha;

import java.util.ArrayList;
import java.util.List;

public class zadacha {

	public static int calc(int k, int l, int m, int n, int d){
		List<Integer> beatedDragons = new ArrayList<Integer>(); 		
		for(int i=0; i<d; i++){
			if((i%k==0)&&(!beatedDragons.contains(i)))
				beatedDragons.add(i);
			if((i%l==0)&&(!beatedDragons.contains(i)))
				beatedDragons.add(i);
			if((i%m==0)&&(!beatedDragons.contains(i)))
				beatedDragons.add(i);
			if((i%n==0)&&(!beatedDragons.contains(i)))
				beatedDragons.add(i);
		}
		for(int i=0; i<beatedDragons.size(); i++){
			System.out.print(beatedDragons.get(i)+"\n");
		}
		return beatedDragons.size();
	}
	
	public static int calc2(int k, int l, int m, int n, int d){
		List<Integer> beatedDragons = new ArrayList<Integer>();
		for(int i=0; i<d; i++){
			beatedDragons.add(i);
		}
		for(int i=0; i<beatedDragons.size(); i++){
			if(beatedDragons.get(i)%k==0){
				beatedDragons.remove(i);
				i--;
				continue;
			}
			if(beatedDragons.get(i)%l==0){
				beatedDragons.remove(i);
				i--;
				continue;
			}
			if(beatedDragons.get(i)%m==0){
				beatedDragons.remove(i);
				i--;
				continue;
			}
			if(beatedDragons.get(i)%n==0){
				beatedDragons.remove(i);
				i--;
				continue;
			}

		}
		return d-beatedDragons.size();
	}
	public static void main(String[] args) {
		System.out.print("result : "+calc2(1,2, 3, 4, 12));
		

	}

}
