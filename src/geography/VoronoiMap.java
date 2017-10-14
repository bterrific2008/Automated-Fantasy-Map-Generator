package geography;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VoronoiMap{
//	private BufferedImage I;
//	private int color[];
	private int numberOfPoints = 256, size = 1000;
	private Point[] pointArray;

	public VoronoiMap(){

		int n = 0;
//		I = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		pointArray = new Point[numberOfPoints];
//		color = new int[numberOfPoints];
		Polygon[] polygons = new Polygon[numberOfPoints];
		System.out.println("hi");
		ArrayList<PointDistance> pD = new ArrayList<PointDistance>();
		System.out.println(":");
		for(int i = 0; i < numberOfPoints; i++){
			pointArray[i] = new Point((int)(Math.random()*size), (int)(Math.random()*size));
//			color[i] = (int)(Math.random()*16777215);
			polygons[i] = new Polygon();
		}
		System.out.println("ik");
		pD.add(new PointDistance(0,pointArray));
		for(int i = 0; i<numberOfPoints; i++){
			pD.add(new PointDistance(i,pointArray));
		}

		pointArray = sortAscending(pointArray);

//		Graphics2D g = I.createGraphics();
//		g.setColor(Color.BLACK);
//		for (int i = 0; i < numberOfPoints; i++) {
//			g.fillRect(pointArray[i].x, pointArray[i].y, 1, 1);
//		}


		for(int x = 0; x < size; x++){
			for(int y = 0; y<size; y++){
				n = 0;
				
//				for(int i = 0; i < numberOfPoints; i++){
//					double di = distance(pointArray[i].x,x,pointArray[i].y,y);
//					int[] close = pD.get(i).returnClose();
//					double dtemp = distance(pointArray[close[i]].x,x,pointArray[close[i]].y,y);
//					if(dtemp<di)
//						n = i;
//				}
				
				for (int i = 0; i < numberOfPoints; i++) {
					double di = distance(pointArray[i].x,x,pointArray[i].y,y);
					double dtemp = distance(pointArray[n].x,x,pointArray[n].y,y);
					if (di < dtemp) {
						n = i;
 
					}
				}

				//	for(int i = 0; i< numberOfPoints; i++){
				//		double d = distance(pointArray[i].x,x,pointArray[i].y,y); 
				//		int rx = pointArray[i].x;
				//		ArrayList<Point2D.Double> l = pD.get(i).returnDistances();
				//		int rip = (int)l.get(i).getX();
				//		if(d<
				//				distance(pointArray[rip].x,x,pointArray[rip].y,y))
				//			n = i;
				//	}



//				I.setRGB(x, y, color[n]);
				polygons[n].addPoint(x, y);
			}
		}

//		g.setColor(Color.BLACK);
//		for (int i = 0; i < numberOfPoints; i++) {
//			g.fill(new Ellipse2D .Double(pointArray[i].x - 2.5, pointArray[i].y - 2.5, 5, 5));
//		}

//		g.dispose();

//		try {
//			ImageIO.write(I, "png", new File("voronoi1.png"));
//		} catch (IOException e) {}
		
		System.out.println(polygons[0].xpoints);

	}


	
	private double distance(int x1, int x2, int y1, int y2){
		double d = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
		return d;
	}

	private Point[] sortAscending(Point[] arr){
		quicksort(0,arr.length-1,arr);
		return arr;
	}

	private Point[] quicksort(int low, int high, Point[] arr){
		int i = low, j = high;
		int pivot = arr[low + (high-low)/2].x;

		while(i<=j){
			while(arr[i].x<pivot)
				i++;
			while(arr[j].x>pivot)
				j--;
			if(i<=j){
				Point temp = (Point) arr[i].clone();
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}


		}
		if(low<j)
			quicksort(low, j, arr);
		if(i<high)
			quicksort(i,high, arr);
		return arr;

	}

	public static void main(String args[]){
		VoronoiMap v = new VoronoiMap();
//		JOptionPane.showConfirmDialog(null, "Finished!");
	}

}
