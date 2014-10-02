package paetow.seifert.vogelflug;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;


public class KollisionsErkennung {

/**
 * @param bitmap1 First bitmap
 * @param x1 x-position of bitmap1 on screen.
 * @param y1 y-position of bitmap1 on screen.
 * @param bitmap2 Second bitmap.
 * @param x2 x-position of bitmap2 on screen.
 * @param y2 y-position of bitmap2 on screen.
 */
	public static boolean isCollisionDetected(Bitmap bitmap1, Rect source, Rect boundsFirst,
	        Bitmap bitmap2, Rect boundsSecond) {

	    Rect bounds1 = boundsFirst;
	    Rect bounds2 = boundsSecond;

	    if (Rect.intersects(bounds1, bounds2)) {
	    	
	    	Bitmap temp = Bitmap.createBitmap(bitmap1, source.left, source.top, source.right-source.left, source.bottom-source.top);
	    	
	        Rect collisionBounds = getCollisionBounds(bounds1, bounds2);
	        for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
	            for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
	                int bitmap1Pixel = temp.getPixel(i-bounds1.left, j-bounds1.top);
	                if (isFilled(bitmap1Pixel)) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

	public static boolean isCollisionDetected(Bitmap bitmap1, Rect boundsFirst,
	        Bitmap bitmap2, Rect boundsSecond) {

	    Rect bounds1 = boundsFirst;
	    Rect bounds2 = boundsSecond;

	    if (Rect.intersects(bounds1, bounds2)) {
	    	    	
	        Rect collisionBounds = getCollisionBounds(bounds1, bounds2);
	        for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
	            for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
	                int bitmap1Pixel = bitmap1.getPixel(i-bounds1.left, j-bounds1.top);
	                if (isFilled(bitmap1Pixel)) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
    int left = (int) Math.max(rect1.left, rect2.left);
    int top = (int) Math.max(rect1.top, rect2.top);
    int right = (int) Math.min(rect1.right, rect2.right);
    int bottom = (int) Math.min(rect1.bottom, rect2.bottom);
    return new Rect(left, top, right, bottom);
}

private static boolean isFilled(int pixel) {
    return pixel != 0;
}
}