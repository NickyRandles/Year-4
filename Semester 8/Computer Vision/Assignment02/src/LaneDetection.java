import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class LaneDetection {

	
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		JFrame frame = new JFrame("Lane Detection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JLabel grayImage = new JLabel();
	    JLabel edgesImage = new JLabel();
	    JLabel regularImage = new JLabel();
	    frame.add(grayImage, BorderLayout.NORTH);
	    frame.add(edgesImage, BorderLayout.CENTER);
	    frame.add(regularImage, BorderLayout.SOUTH);
	    frame.pack();
	    frame.setVisible(true);
	    
	    VideoCapture video = new VideoCapture();
	    String path = "StayingInLane.avi";
	    //open video file with the path
	    if(!video.open(path)){
	    	System.out.println("Unable to open video");
	    	return;
	    }
	    System.out.println("Video Opened");
	    Mat img = new Mat();
	    Mat gray = new Mat();
	    Mat edges = new Mat();
	    int i=0;
	    while (true){
	    	//find the next frame in the video and assign it to the img mat
	    	if(!video.read(img)){
	    		System.out.println("No frame");
	    		break;
	    	}
	    	else{
	    		//calculate the mean processing time per frame and display it
	    		double before = (double)System.nanoTime()/1000000000;//secs
		   
	    		//takes in the frame and converts it to grayscale
	    		Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
	    		//convert to a Java BufferedImage so we can display in a label
	    		BufferedImage bGray = Mat2BufferedImage(gray);
	            grayImage.setIcon(new ImageIcon(bGray));
	            
	            /* Takes in the grayscale image and finds the edges using 
	               the canny algorithm. It then marks the edges and outputs
	               them in the edges mat. It takes in a lower and upper threshold
	               which are used for edge linking. The largest value is used to 
	               find initial segments of strong edges.
	             */
	    		Imgproc.Canny(gray, edges, 100, 200);
	    		//convert to a Java BufferedImage so we can display in a label
	    		BufferedImage bEdges = Mat2BufferedImage(edges);
	    		edgesImage.setIcon(new ImageIcon(bEdges));
	    		
	    		Mat lines = new Mat();
	    		/*
	    		   Takes in the image and finds the lines by using the probabilistic
	    		   Hough transform. Outputs the lines to the line mat. It finds the
	    		   lines using rho (distance resolution), theto (Angle resolution),
	    		   threshold, minLineLength and maxLineGap.
	    		 */
	    		Imgproc.HoughLinesP(edges, lines, 1, Math.PI / 180, 1, 50, 10);
	 	    	for(int j = 0; j < lines.cols(); j++) {
	    			double[] value = lines.get(0, j);
	    			//draw lines on img using line values
		    		Core.line(img, new Point(value[0], value[1]), new Point(value[2], value[3]), new Scalar(0, 0, 255), 2);
	    		} 		
	    		double after = (double)System.nanoTime()/1000000000;//secs
	    		//write the text string below into the image
	    		Core.putText(img, "Processing Time: " + String.format("%.4f",after-before) + " secs", new Point(20,20), Core.FONT_HERSHEY_PLAIN, 2, new Scalar(0,255,0));
	    		//convert to a Java BufferedImage so we can display in a label
	    		BufferedImage bImg = Mat2BufferedImage(img);
	    		regularImage.setIcon(new ImageIcon(bImg));
	    		frame.pack();
	    	}
	    }
	    
	}
	
	public static BufferedImage Mat2BufferedImage(Mat m){
		// source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
		// Fastest code
		// The output can be assigned either to a BufferedImage or to an Image

		int type = BufferedImage.TYPE_BYTE_GRAY;
		if ( m.channels() > 1 ) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels()*m.cols()*m.rows();
		byte [] b = new byte[bufferSize];
		m.get(0,0,b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);  
		return image;
	}

}
