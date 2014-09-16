package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
	private Bitmap background;
	private GameView theGameView;
	private int yScroll = 0;
	private Rect source, source2, destine;

	public Background(Bitmap sourceBackground, GameView gameView) {
		this.background = sourceBackground;
		this.theGameView = gameView;
	}

	public void scroll() {
		
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
		}

	}

	public void onDraw(Canvas canvas) {
		scroll();
		canvas.drawBitmap(background, source, destine, null);
		canvas.drawBitmap(background, source2, destine, null);
	}
}