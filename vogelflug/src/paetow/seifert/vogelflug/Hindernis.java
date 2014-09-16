package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Hindernis {
	
	private Bitmap wallLeft, wallRight;
	private GameView theGameView;
	private int yScroll = 0;
	private Rect sourceLeft, source2Left, source3Left, sourceRight, source2Right, source3Right, destineLeft, destineRight;

	public Hindernis (Bitmap WallLeft,Bitmap WallRight, GameView gameView) {
		this.wallLeft = WallLeft;
		this.wallRight = WallRight;
		this.theGameView = gameView;
	}

	public void scroll() {
		
		sourceLeft = new Rect(0, yScroll, 50, yScroll + theGameView.getHeight());
		source2Left = new Rect(0, yScroll - wallLeft.getHeight(), 50, yScroll - wallLeft.getHeight() 
				+ theGameView.getHeight());
		source3Left = new Rect(0, yScroll - 2*wallLeft.getHeight(), 50, yScroll - 2*wallLeft.getHeight() 
				+ theGameView.getHeight());	
		destineLeft = new Rect(0, 0, 50,theGameView.getHeight());
		
		sourceRight = new Rect(0, yScroll, 50, yScroll + theGameView.getHeight());
		source2Right = new Rect(0, yScroll - wallRight.getHeight(), 50, yScroll - wallRight.getHeight() 
				+ theGameView.getHeight());	
		source3Right = new Rect(0, yScroll - 2*wallRight.getHeight(), 50, yScroll - 2*wallRight.getHeight() 
				+ theGameView.getHeight());
		destineRight = new Rect(theGameView.getWidth()-50, 0, theGameView.getWidth(), theGameView.getHeight());

		if (yScroll < (wallRight.getHeight()-5)) {
			yScroll += 10;
		} 
		else {
			yScroll = 0;
		}

	}

	public void onDraw(Canvas canvas) {
		scroll();
		canvas.drawBitmap(wallLeft, sourceLeft, destineLeft, null);
		canvas.drawBitmap(wallLeft, source2Left, destineLeft, null);
		canvas.drawBitmap(wallLeft, source3Left, destineLeft, null);
		

		canvas.drawBitmap(wallRight, sourceRight, destineRight, null);
		canvas.drawBitmap(wallRight, source2Right, destineRight, null);
		canvas.drawBitmap(wallRight, source3Right, destineRight, null);
	}
}

