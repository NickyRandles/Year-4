package labelling;

import java.util.ArrayList;

public class ConnectedComponents02 {

	public static int[][] labelling(int[][] image){
		int[][] label = new int[image.length][image[0].length];
		int curlab = 0;
		int[][] equal = new int[1000][1000];
		int c = 0;
		ArrayList<int[]> list = new ArrayList<int[]>();
		int[] temp;
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){
				if(image[i][j] == 1){
					if(label[i-1][j] == 0 && label[i][j-1] == 0){
						label[i][j] = curlab++;
					}
					else if(label[i-1][j] != 0 && label[i][j-1] == 0){
						label[i][j] = label[i-1][j];
					}
					else if(label[i-1][j] == 0 && label[i][j-1] != 0){
						label[i][j] = label[i][j-1];
					}
					else if(label[i-1][j] != 0 && label[i][j-1] != 0){
						temp = new int[2];
						if(label[i-1][j] < label[i][j-1]){
							label[i][j] = label[i-1][j];
							temp[0] = label[i-1][j];
							temp[1] = label[i][j-1];
							list.add(temp);
						}
						else if(label[i-1][j] > label[i][j-1]){
							label[i][j] = label[i][j-1];
							temp[0] = label[i][j-1];
							temp[1] = label[i-1][j];
							list.add(temp);
						}
					
					}
				}
			}
		}
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){
				for(int l = 0; l < list.size(); l++){
					temp = list.get(l);
					if(label[i][j] == temp[1]){
						System.out.println(temp[0] + " " + temp[1]);
						label[i][j] = temp[0];
					}
				}
			}
		}
		
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){
				if(label[i][j] == 1){
					image[i][j] = 1;
				}
				else{
					image[i][j] = 0;
				}
			}
		}
		
		
		for(int[] row : label) {
            printRow(row);
        }	
		
		return image;
		
	}
	
	public static void printRow(int[] row) {
	    for (int i : row) {
	        System.out.print(i);
	        System.out.print("\t");
	    }
	    System.out.println();
	}
}
