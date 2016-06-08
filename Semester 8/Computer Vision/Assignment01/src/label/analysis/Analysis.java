package label.analysis;

import java.awt.Font;

import javax.swing.JLabel;

import org.opencv.core.Mat;

public class Analysis {

	public static void analysis(Mat img, JLabel status){
	byte data[] = new byte[img.rows()*img.cols()*img.channels()];
	img.get(0, 0, data);
	int temp[] = new int[data.length];
	for(int i = 0; i < data.length; i++){
		   temp[i] = (data[i] & 0xff)/ 255;
	   }
   
   int image[][] = new int[img.cols()][img.rows()];
   for(int i = 0; i < image.length; i++){
	   for(int j = 0; j < image[0].length; j++){
		   image[i][j] = (int) temp[(i * image.length) + j];
	   }
   }
		int xmin = image.length-1;
		int ymin = image[0].length-1;
		int xmax = 0;
		int ymax = 0;
		int x1,y1,x2,y2;
		for(int i = 0; i < image.length; i++){
		   for(int j = 0; j < image[0].length; j++){
			   int currentPixel = image[i][j];
			   if(currentPixel != 0){
				   int currentx = i;
				   int currenty = j;
				   if(currentx > xmax){
					   xmax = currentx;
				   }
				   if(currentx < xmin){
					   xmin = currentx;
				   }
				   if(currentx > ymax){
					   ymax = currenty;
				   }
				   if(currentx < xmin){
					   ymin = currenty;
				   }		
			   }
		   }
		}
		x1 = xmin-1;
		y1 = ymin-1;
		x2 = xmax+1;
		y2 = ymax+1;
		
		int area = 0;
	    int perimeter = 0;
	    for(int i = 0; i < image.length; i++){
		    for(int j = 0; j < image[0].length; j++){
			    if(image[i][j] == 1){
				    area++;
				    if(image[i-1][j] == 0 || image[i][j-1] == 0){
					    perimeter++;
				    }
			    }
		    }
	    }
	  
	    System.out.println(area);
	    System.out.println(perimeter);
	    //double circularity = perimeter / (2+Math.sqrt(3.14159*area));
	    double circularity = 4*3.14159*(area/Math.pow(perimeter, 2));
	   
	    System.out.println(circularity);
	    if(circularity > 0.2){
		    status.setText("Pass");
		    status.setFont(new Font("Ariel", Font.PLAIN, 20));
	    } 
	    else{
		    status.setText("Fail");
		    status.setFont(new Font("Ariel", Font.PLAIN, 20));
	    }
	
	}
}
