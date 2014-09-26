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

	public Background(GameView gameView) {
		this.theGameView = gameView;
		this.background = gameView.getBackground(1);
		this.backgroundNext = gameView.getBackground(2);
	}

	private void getBackgroundSource() {
		if (level == 1) {
			background = theGameView.getBackground(1);
			backgroundNext = theGameView.getBackground(2);

		}

		if (level == 2) {
			background = theGameView.getBackground(2);
			backgroundNext = theGameView.getBackground(3);
		}

		if (level == 3) {
			background = backgroundNext;
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
			yScroll+=5;
		} else {
			yScroll = 0;
			level++;
			getBackgroundSource();
		}

	}

	public void onDraw(Canvas canvas) {

		scroll();
		canvas.drawBitmap(background, source, destine, null);
		canvas.drawBitmap(backgroundNext, source2, destine, null);
	}
}