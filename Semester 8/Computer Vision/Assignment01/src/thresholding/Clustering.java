package thresholding;

import org.opencv.core.Mat;

public class Clustering {
	
	public static int clustering(Mat img){
		byte srcData[] = new byte[img.rows()*img.cols()*img.channels()];
		img.get(0, 0, srcData);
		int total = 0;
		for(int i = 0; i < srcData.length; i++){
			total += srcData[i] & 0xff;
		}
		int t = total / srcData.length;
		int c1 = 0;
		int c1Amount = 0;
		int c2 = 0;
		int c2Amount = 0;
		for(int i = 0; i < srcData.length; i++){
			int value = srcData[i] & 0xff;
			total += value;
			if(value > t){
				c1 += value;
				c1Amount++;
			}
			else if(value <= t){
				c2 += value;
				c2Amount++;
			}
		}
		int u1 = c1 / c1Amount;
		int u2 = c2 / c2Amount;
		
		t = (u1 + u2) / 2;
		return t;
	}
}
