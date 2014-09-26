package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
	private Bitmap background;
	private Bitmap backgroundNext;
	private GameView theGameView;
	private int yScroll = 0;
	private Rect source, source2, destine;
	private int level = 1;
	private boolean change = true;

	public Background(GameView gameView) {
		this.theGameView = gameView;
	}

	private void getBackgroundSource ()
	{
		if (level == 1) 
		{
			background = theGameView.getBackground(1);
			backgroundNext = theGameView.getBackground(2);
			change = false;
		}
		
		else if (level ==2)
		{
			background = theGameView.getBackground(2);
			backgroundNext = theGameView.getBackground(3);
			change = false;
		}
		
	}
	
	
	private void scroll() {
		
		source = new Rect(0, yScroll, theGameView.getWidth(), yScroll
				+ theGameView.getHeight());
		source2 = new Rect(0, yScroll - background.getHeight(),
				theGameView.getWidth(), yScroll - background.getHeight()
						+ theGameView.getHeight());
		destine = new Rect(0, 0, theGameView.getWidth(),
				theGameView.getHeight());

		if (yScroll < background.getHeight()) {
			yScroll++;
		} 
		else {
			yScroll = 0;
			level ++; change = true;
		}

	}

	public void onDraw(Canvas canvas) {
		
		if (change){getBackgroundSource();}
		scroll();
		canvas.drawBitmap(background, source, destine, null);
		canvas.drawBitmap(backgroundNext, source2, destine, null);
	}
}