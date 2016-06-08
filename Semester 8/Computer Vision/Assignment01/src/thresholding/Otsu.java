package thresholding;

import org.opencv.core.Mat;

public class Otsu {
	private int otsu(Mat img, int hist[]) {
		int threshold;
		// Calculate histogram
		byte srcData[] = new byte[img.rows()*img.cols()*img.channels()];
		img.get(0, 0, srcData);	
			
		int ptr = 0;
		while (ptr < srcData.length) {
			int h = 0xFF & srcData[ptr];
			hist[h] ++;
			ptr ++;
		}
		
		// Total number of pixels
		int total = srcData.length;
		
		float sum = 0;
		for (int t=0 ; t<256 ; t++) sum += t * hist[t];
		
		float sumB = 0;
		int wB = 0;
		int wF = 0;
		
		float varMax = 0;
		threshold = 0;
		
		for (int t=0 ; t<256 ; t++) {
		wB += hist[t];               // Weight Background
		if (wB == 0) continue;
		
		wF = total - wB;                 // Weight Foreground
		if (wF == 0) break;
		
		sumB += (float) (t * hist[t]);
		
		float mB = sumB / wB;            // Mean Background
		float mF = (sum - sumB) / wF;    // Mean Foreground
		
		// Calculate Between Class Variance
		float varBetween = (float)wB * (float)wF * (mB - mF) * (mB - mF);
		
		// Check if new maximum found
		if (varBetween > varMax) {
			varMax = varBetween;
				threshold = t;
			}
		}
		System.out.println(threshold);
		return threshold;
	}
}
