package geography;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PointDistance{

	ArrayList<Point2D.Double> distances = new ArrayList<Point2D.Double>();

	public PointDistance(int n, Point[] points){
		Point nPoint = points[n];
		for(int p = 0; p < points.length; p++){
		
			if (p<points.length){
				Point temp = points[p];
				double tempD = distance(nPoint.x, temp.x, nPoint.y, temp.y);
				if(distances.size()==0)
					distances.add(new Point2D.Double(p, tempD));
				else{
					for(int i = 0; i < distances.size(); i++){
						if(distances.get(i).getY()>tempD){
							distances.add(i, new Point2D.Double(p, tempD));
							break;
						}
						else if(i+1==distances.size()){
							distances.add(new Point2D.Double(p, tempD));
							break;
						}
					}
				}	
			}
		}
	}

	private double distance(int x1, int x2, int y1, int y2){
		double d = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
		return d;
	}

	public int[] returnClose(){
		int[] distance = new int[distances.size()];
		for(int i = 0; i<distance.length; i++)
			distance[i] = (int)distances.get(i).getX();
		return distance;
	}

}
