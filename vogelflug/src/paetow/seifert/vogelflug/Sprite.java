package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

@SuppressLint("DrawAllocation")
public class Sprite {

	private GameView theGameView;
	private int actualPos;
	private int[] posX = new int[4];
	private int countArray;
	private int y;
	private int xSpeed = 30;

	private int animate;
	boolean bounced = false;

	private Bitmap bmp;
	private Bitmap bmpFly; // Bitmap einlesen
	private Bitmap bmpFloat;
	private int width;
	private int height;

	private final int BMP_COLUMNS = 4;
	private final int BMP_ROWS = 4;
	private int spriteRow = 0; // von 0 - 3 fuer die jeweiligen Richtungssprites
	private int frameZeiger = 0;

	public Sprite(Bitmap bmpFly, Bitmap bmpFloat, GameView theGameView) {
		super();
		this.width = bmpFly.getWidth() / BMP_COLUMNS;
		this.height = bmpFly.getHeight() / BMP_ROWS;
		this.theGameView = theGameView;
		this.bmpFloat = bmpFloat;
		this.bmpFly = bmpFly;
		this.bmp = bmpFloat;
		actualPos = posX[0];
		countArray = 0;
	}

	public void bounceOff() {
		if (countArray >= posX.length) {

			if ((actualPos < theGameView.getWidth() - 2 * width - xSpeed)
					&& bounced == false) {
				actualPos += xSpeed;
			} else {
				bounced = true;
				if (actualPos > posX[3]) {
					actualPos -= xSpeed;
				} else {
					animate = 0;
					countArray -= 1;
					bounced = false;
				}
			}
		} else {
			if ((actualPos > xSpeed) && bounced == false) {
				actualPos -= xSpeed;
			} else {
				bounced = true;
				if (actualPos < posX[countArray + 1]) {
					actualPos += xSpeed;
				} else {
					animate = 0;
					countArray += 1;
					bounced = false;
				}
			}
		}

	}

	public void moveRight() {
		bmp = bmpFly;
		spriteRow = 1;
		animate = 1;
		if (countArray < posX.length) {
			countArray += 1;
		}
	}

	public void moveLeft() {
		bmp = bmpFly;
		spriteRow = 0;
		animate = 2;
		if (countArray > -1) {
			countArray -= 1;
		}

	}

	public void moveUp() {
		bmp = bmpFly;
		spriteRow = 2;
		animate = 3;
	}

	public void moveDown() {
		bmp = bmpFly;
		spriteRow = 3;
		animate = 4;
	}

	public void hoverLeft() {
		bmp = bmpFly;
		spriteRow = 1;
	}

	public void hoverRight() {
		bmp = bmpFloat;
		spriteRow = 1;
	}

	public void goRight() {
		if (actualPos < posX[countArray]) {
			actualPos += xSpeed;
		} else {
			bmp=bmpFloat;
			hoverRight();
			animate = 0;
		}
	}

	public void goLeft() {
		if (actualPos > posX[countArray]) {
			actualPos -= xSpeed;

		} else {
			bmp=bmpFloat;
			hoverLeft();
			animate = 0;
		}

	}

	public void onDraw(Canvas canvas) {

		posX[0] = theGameView.getWidth() / 15;
		posX[1] = theGameView.getWidth() / 4 + 20;
		posX[2] = 2 * theGameView.getWidth() / 4 + 30;
		posX[3] = 3 * theGameView.getWidth() / 4;
		y = theGameView.getHeight() / 2;

		frameZeiger = (++frameZeiger) % BMP_COLUMNS;

		switch (animate) {
		case 0: {
			bmp = bmpFloat;
			spriteRow = 2;

		}
		case 1: {
			if (countArray < posX.length) {
				goRight();
			} else {
				bounceOff();
			}
		}
		case 2: {
			if (countArray >= 0 && countArray < posX.length) {
				goLeft();
			} else {
				bounceOff();
			}
		}
		}

		int sourceX = frameZeiger * width;
		int sourceY = spriteRow * height;
		Rect source = new Rect(sourceX, sourceY, sourceX + width, sourceY
				+ height); // Rechteck mit den jeweiligen Eckkoordinaten des
							// Sprite-Frames
		Rect destine = new Rect(actualPos + (width / 2), y, actualPos
				+ (5 * width / 2), y + (2 * height));

		canvas.drawBitmap(bmp, source, destine, null);

	}

}