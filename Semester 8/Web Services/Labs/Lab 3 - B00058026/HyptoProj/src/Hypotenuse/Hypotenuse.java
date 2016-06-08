package Hypotenuse;

import javax.jws.WebService;

@WebService
public class Hypotenuse {

	public void constructor(){
		
	}
	
	public double hypotenuse(double side1, double side2){
		
		return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
	}
}
