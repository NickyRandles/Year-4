package Numbers;

import javax.jws.WebService;

@WebService
public class Numbers {

	public void constructor(){
		
	}
	
	public int smallest(int num1, int num2, int num3){
		int smallest = num1;
		if(num2 < smallest){
			smallest = num2;
		}
		if(num3 < smallest){
			smallest = num3;
		}
		return smallest;
	}
}
