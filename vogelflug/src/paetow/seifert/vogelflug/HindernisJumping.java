package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisJumping extends HindernisAbstract {

	private final int BMP_COLUMNS = 5;
	private final int BMP_ROWS = 2;
	private int spriteRow = 0; // 0 fuer Flug von Rechts, 1 fuer Flug von Links
	private int frameZeiger = 0;
	private boolean first;
	private int xPos;

	public HindernisJumping(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.bmp = bmp;
		this.xSpeed = -30;
		this.ySpeed = -15;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.draw = false;
		this.theSprite = theSprite;
		this.destine = new Rect();
	}

	public void setdraw() {
		draw = true;
	}

	@SuppressLint("DrawAllocation") @Override
	public void onDraw(Canvas canvas) {
		if (first == true) {
			xPos = theGameView.getWidth();
			yPos = theGameView.getHeight() / 3;
		}
		if (draw == true) {

			int sourceX = frameZeiger * width;
			int sourceY = spriteRow * height;

			source = new Rect(sourceX, sourceY, sourceX + width, sourceY
					+ height);

			destine = new Rect(xPos, theGameView.getHeight() - yPos, width
					+ xPos, theGameView.getHeight() + height - yPos);

			frameZeiger = ++frameZeiger % BMP_COLUMNS;

			canvas.drawBitmap(bmp, source, destine, null);
			yPos += ySpeed;
			xPos += xSpeed;
			if (xPos >= theGameView.getWidth() + 100) {
				draw = false;
				yPos = 900;
				xPos = theGameView.getWidth();
				xSpeed = -30;
				spriteRow = 0;
			}
		}
		if (xPos <= -250) {
			draw = false;
			yPos = 900;
			xPos = 0;
			xSpeed = 30;
			spriteRow = 1;

		}

		if(KollisionsErkennung.isCollisionDetected(bmp, destine,
				theSprite.getBitmap(), theSprite.getDestine())){
			new CustomTask().execute(-1);
		}
	}
}