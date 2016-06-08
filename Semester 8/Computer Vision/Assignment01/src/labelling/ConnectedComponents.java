package labelling;

import java.util.Stack;

import org.opencv.core.Point;

public class ConnectedComponents {
	public int[][] labelling(int[][] image){
		int curlab = 1;
		int[][] label = new int[image.length][image[0].length];
		Stack<Point> stack = new Stack<Point>();
		Point point = new Point();
		Point temp = new Point();
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){		
				if(image[i][j] != 0){	
					label[i][j] = curlab;
					point = new Point();
					point.x = i;
					point.y = j;
					stack.push(point);
					while(!stack.empty()){
						point = stack.pop();
						temp = new Point();
						if(point.x-1 > 0){
							temp.x = point.x-1;
							temp.y = point.y;
							if(image[(int)temp.x][(int)temp.y] == 1 && label[(int)temp.x][(int)temp.y] == 0){
								label[(int)temp.x][(int)temp.y] = curlab;
								stack.push(temp);
							}						
						}
						if(point.y-1 > 0){
							temp.x = point.x;
							temp.y = point.y-1;
							if(image[(int)temp.x][(int)temp.y] == 1 && label[(int)temp.x][(int)temp.y] == 0){
								label[(int)temp.x][(int)temp.y] = curlab;
								stack.push(temp);
							}
						}
						if(point.x+1 < image.length){
							temp.x = point.x+1;
							temp.y = point.y;
							if(image[(int)temp.x][(int)temp.y] == 1 && label[(int)temp.x][(int)temp.y] == 0){
								label[(int)temp.x][(int)temp.y] = curlab;
								stack.push(temp);
							}
						}
						if(point.y+1 < image[i].length){
							temp.x = point.x;
							temp.y = point.y+1;
							if(image[(int)temp.x][(int)temp.y] == 1 && label[(int)temp.x][(int)temp.y] == 0){
								label[(int)temp.x][(int)temp.y] = curlab;
								stack.push(temp);
							}
						}
					}
				}
			} 
		}
		for(int[] row : label) {
	        printRow(row);
	    }
		
		  return label;
	}
		
	public static void printRow(int[] row) {
	    for (int i : row) {
	        System.out.print(i);
	        System.out.print("\t");
	    }
	    System.out.println();
	}
}
