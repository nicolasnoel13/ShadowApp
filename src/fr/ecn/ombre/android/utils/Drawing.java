package fr.ecn.ombre.android.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import fr.ecn.common.core.geometry.Point;
import fr.ecn.common.core.imageinfos.Face;
import fr.ecn.ombre.core.shadows.ShadowDrawingFace;

/**
 * @author jerome
 * 
 * A class that provide methods to draw Objects into an android canvas
 */
public class Drawing {
	
	/**
	 * Draw a given Face in a given Canvas using a given Paint
	 * 
	 * @param face the face to be drawn
	 * @param canvas the canvas to draw into
	 * @param paint the paint to use to draw
	 * @param partial if the face is partial, a partial face doesn't have the first and last point linked
	 */
	public static void drawFace(Face face, Canvas canvas, Paint paint, boolean partial) {
		if (face.getPoints().size()!=0) {

			Point[] points = face.getPoints().toArray(new Point[0]);

			Path path = new Path();

			path.moveTo((float) points[0].getX(), (float) points[0].getY());

			// We draw a circle around the first point to show it has been taken into account
			if (points.length==1){
				canvas.drawCircle((float)points[0].getX(), (float)points[0].getY(), 2, paint);
			}
			else {
				for (int i = 1; i < points.length; i++) {
					Point p = points[i];

					path.lineTo((float) p.getX(), (float) p.getY());
				}
					path.lineTo((float) points[0].getX(), (float) points[0].getY());
			}

			canvas.drawPath(path, paint);
		}
	}

	public static void drawFace(Face face, Canvas canvas, Paint paint) {
		drawFace(face, canvas, paint, face.isPartial());
	}
	
	/**
	 * Draw a given Shadow in a given Canvas using a given Paint
	 * 
	 * @param face the face to be drawn
	 * @param canvas the canvas to draw into
	 * @param paint the paint to use to draw
	 */
	public static void drawShadow(ShadowDrawingFace face, Canvas canvas, Paint paint) {
		Point[] points = face.getPoints();
		
		Path path = new Path();
		path.moveTo((float) points[points.length-1].getX(), (float) points[points.length-1].getY());

		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			
			path.lineTo((float) p.getX(), (float) p.getY());
		}
		
		canvas.drawPath(path, paint);
	}
}
