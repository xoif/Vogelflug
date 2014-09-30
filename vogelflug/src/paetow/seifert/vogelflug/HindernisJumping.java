package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisJumping extends HindernisAbstract {

	private final int BMP_COLUMNS = 3;
	private final int BMP_ROWS = 2;
	private int spriteRow = 1; // 0 fuer Flug von Rechts, 1 fuer Flug von Links
	private int frameZeiger = 0;
	private boolean first;
	private int vorzeichen = -1;
	private boolean wait;

	public HindernisJumping(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.bmp = bmp;
		this.xSpeed = 30;
		this.ySpeed = -10;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.draw = false;
		this.theSprite = theSprite;
		this.destine = new Rect();
		this.xPos = -width/2;
		this.yPos = 0;
		this.wait = true;
	}

	public void setdraw() {
		draw = true;
		wait = true;
		HindernisManager.setUnabled();
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		if (draw == true) {

			if (wait == true) {
	
				int sourceX = frameZeiger * width;
				int sourceY = spriteRow * height;

				source = new Rect(sourceX, sourceY, sourceX + width, sourceY
						+ height);

				destine = new Rect(xPos, theGameView.getHeight() - yPos, xPos + width,
						theGameView.getHeight() + height - yPos);
				canvas.drawBitmap(bmp, source, destine, null);
				yPos -= ySpeed;
				if (yPos > 2 * theGameView.getHeight() / 3){
					wait = false;
				}

			}

			else {
				
				int sourceX = frameZeiger * width;
				int sourceY = spriteRow * height;

				source = new Rect(sourceX, sourceY, sourceX + width, sourceY
						+ height);
				
				destine = new Rect(xPos, theGameView.getHeight() - yPos, width
						+ xPos, theGameView.getHeight() + height - yPos);

				if (frameZeiger == BMP_COLUMNS - 1 || frameZeiger == 0) {
					vorzeichen *= -1;
				}
				frameZeiger += vorzeichen;

				canvas.drawBitmap(bmp, source, destine, null);
				yPos += ySpeed;
				xPos += xSpeed;
				
				if (xPos >= theGameView.getWidth() + 2*width) {
					draw = false;
					yPos = 0;
					xPos = theGameView.getWidth()- width/2;
					xSpeed = -30;
					spriteRow = 0;
					HindernisManager.setEnabled();
				}

				if (xPos <= -width) {
					draw = false;
					yPos = 0;
					xPos = -width/2;
					xSpeed = 30;
					spriteRow = 1;
					HindernisManager.setEnabled();
				}
			}
		}
		if (KollisionsErkennung.isCollisionDetected(bmp, source, destine,
				theSprite.getBitmap(), theSprite.getDestine())) {
			new CustomTask().execute(-1);
		}
	}
}