package geography;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VoronoiPanel extends JPanel{

	//http://www.cs.sfu.ca/~binay/813.2011/Fortune.pdf

	int lineSweep = 0;
	int pointIndex = 0;
	int temp = 0;

	int size = 1000, points = 256;
	Random r = new Random();
	int[] px = new int[points], py = new int[points];
	int[] y0 = new int[points], ySize = new int[points]; 
	Color[] c = new Color[points];
	double[] m = new double[points];

	public VoronoiPanel()
	{
		generatePoints();
		startLine();
		while(lineSweep!=size){
			advanceLine();
		}

	}

	private void startLine(){
		while(px[pointIndex]!=lineSweep){
			while(px[pointIndex]<lineSweep){
				pointIndex++;
			}
			while(px[pointIndex]>lineSweep){
				lineSweep++;
			}
		}

	}

	private void advanceLine(){
		
		//check for point Events
		if(pointIndex!=points){
			while(lineSweep==px[pointIndex]){
				pointEvent();
				pointIndex++;
				if(pointIndex==points)
					break;
			}		
		}
		lineSweep = lineSweep + 1;	
		
		//somehow check for Vertex Events
		
	}

	private void pointEvent(){
		temp++;
	}

	private void vertexEvent(){

	}

	private void generatePoints(){
		for(int i = 0; i<points; i++){
			px[i] = r.nextInt(size);
			py[i] = r.nextInt(size);
		}
		Arrays.sort(px);
	}

	private double eDistance(int x1, int x2, int y1, int y2){
		double d = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
		return d;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		g.setColor(Color.RED);
		for(int i = 0; i<points; i++){
			g.fillOval(px[i], py[i], 3, 3);
		}

	}

	public static void main(String args[]){
		JFrame frame = new JFrame();
		VoronoiPanel contentPane = new VoronoiPanel();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().add(contentPane);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}

}
