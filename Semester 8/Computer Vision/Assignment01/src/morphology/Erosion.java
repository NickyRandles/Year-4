package morphology;

public class Erosion {
	public int[][] erosion(int[][] image){	
	    for (int i=0; i<image.length; i++){
	        for (int j=0; j<image[i].length; j++){
	            if (image[i][j] == 0){
	                if (i>0 && image[i-1][j]==1) image[i-1][j] = 2;
	                if (j>0 && image[i][j-1]==1) image[i][j-1] = 2;
	                if (i+1<image.length && image[i+1][j]==1) image[i+1][j] = 2;
	                if (j+1<image[i].length && image[i][j+1]==1) image[i][j+1] = 2;
	            }
	        }
	    }
	    for (int i=0; i<image.length; i++){
	        for (int j=0; j<image[i].length; j++){
	            if (image[i][j] == 2){
	                image[i][j] = 0;
	            }
	        }
	    }
	    return image;
	}
	public int[][] erosion(int[][] image, int k){
	    for (int i=0; i<k; i++){
	        image = erosion(image);
	    }
	    return image;
	}
}
