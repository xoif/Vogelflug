package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

@SuppressLint("DrawAllocation")
public class Sprite {

	private GameView theGameView;
	private int actualX;
	private int[] posX = new int[4];
	private int countArray;
	private int y, actualY, yUp, yDown;
	private int xSpeed = 45;
	private int ySpeed = 30;

	private int animateVer, animateHor;
	boolean bouncedVer = false;
	boolean bouncedHor = false;
	boolean first = true;

	private Bitmap bmp;
	private Bitmap bmpFly; // Bitmap einlesen
	private Bitmap bmpFloat;
	private int width;
	private int height;
	private Rect source, destine;

	private final int BMP_COLUMNS = 4;
	private final int BMP_ROWS = 4;
	private int spriteRow = 2; // von 0 - 3 fuer die jeweiligen Richtungssprites
	private int frameZeiger = 0;

	public Sprite(Bitmap bmpFly, Bitmap bmpFloat, GameView theGameView) {
		super();
		this.width = bmpFly.getWidth() / BMP_COLUMNS;
		this.height = bmpFly.getHeight() / BMP_ROWS;
		this.theGameView = theGameView;
		this.bmpFloat = bmpFloat;
		this.bmpFly = bmpFly;
		this.bmp = bmpFloat;
		actualX = posX[0];
		countArray = 0;
	}

	public void moveRight() {
		bmp=bmpFly;
		spriteRow = 1;
		animateHor = 1;
		bouncedHor = false;
		if (countArray < posX.length) {
			countArray += 1;
		}
	}

	public void moveLeft() {
		bmp=bmpFly;
		spriteRow = 0;
		animateHor = 2;
		bouncedHor = false;
		if (countArray > -1) {
			countArray -= 1;
		}

	}

	public void moveUp() {
		bmp = bmpFly;
		spriteRow = 2;
		animateVer = 3;
		bouncedVer = false;
	}

	public void moveDown() {
		bmp = bmpFly;
		spriteRow = 3;
		animateVer = 4;
		bouncedVer = false;
	}

	public void goRight() {
		if (actualX < posX[countArray]) {
			actualX += xSpeed;
		} else {
			animateHor = 0;
		}
	}

	public void goLeft() {
		if (actualX > posX[countArray]) {
			actualX -= xSpeed;
		} else {
			animateHor = 0;
		}
	}
	
	public void bounceOff() {
		if (countArray >= posX.length) {

			if ((actualX < theGameView.getWidth() - 2 * width - xSpeed)
					&& bouncedHor == false) {
				actualX += xSpeed;
			} else {
				bouncedHor = true;
				spriteRow = 0;
				if (actualX > posX[3]) {
					actualX -= xSpeed;
				} else {
					animateHor = 0;
					countArray -= 1;
					bouncedHor = false;
				}
			}
		} else {
			if ((actualX > xSpeed) && bouncedHor == false) {
				actualX -= xSpeed;
			} else {
				bouncedHor = true;
				spriteRow = 1;
				if (actualX < posX[countArray + 1]) {
					actualX += xSpeed;
				} else {
					animateHor = 0;
					countArray += 1;
					bouncedHor = false;
				}
			}
		}
	}

	public void goUp() {
		if (actualY > yUp && bouncedVer == false) {
			actualY -= ySpeed;
		} else {
			bouncedVer = true;
			spriteRow = 3;
			if (actualY < y) {
				actualY += ySpeed;
			} else {
				animateVer = 0;
				bouncedVer = false;
			}
		}

	}

	public void goDown() {
		if (actualY < yDown && bouncedVer == false) {
			actualY += ySpeed;
		} else {
			bouncedVer = true;
			spriteRow = 2;
			if (actualY > y) {
				actualY -= ySpeed;
			} else {
				animateVer = 0;
				bouncedVer = false;
			}
		}
	}

	public void onDraw(Canvas canvas) {
		if (first == true) {
			posX[0] = theGameView.getWidth() / 15;
			posX[1] = theGameView.getWidth() / 4 + 20;
			posX[2] = 2 * theGameView.getWidth() / 4 + 30;
			posX[3] = 3 * theGameView.getWidth() / 4;
			y = theGameView.getHeight() / 2;
			actualY = y;
			yUp = y - 300;
			yDown = y + 300;
			first = false;
		}

		frameZeiger = (++frameZeiger) % BMP_COLUMNS;
		
		if((animateHor == 0)&&(animateVer == 0)){
			bmp = bmpFloat;
			spriteRow = 2;
		}
		
		if(animateHor == 1) {
			if (countArray < posX.length) {
				goRight();
			} else {
				bounceOff();
			}
		}
		if(animateHor == 2) {
			if (countArray >= 0 && countArray < posX.length) {
				goLeft();
			} else {
				bounceOff();
			}
		}
		if(animateVer == 3) {
			goUp();
		}
		if(animateVer == 4) {
			goDown();
		}
		
		int sourceX = frameZeiger * width;
		int sourceY = spriteRow * height;
		source = new Rect(sourceX, sourceY, sourceX + width, sourceY
				+ height); // Rechteck mit den jeweiligen Eckkoordinaten des
							// Sprite-Frames
		destine = new Rect(actualX + (width / 2), actualY, actualX
				+ (5 * width / 2), actualY + (2 * height));

		canvas.drawBitmap(bmp, source, destine, null);
		
	}
	
	public Rect getDestine(){
		return new Rect(actualX + (width / 2), actualY, actualX
				+ (5 * width / 2), actualY + (2 * height));
	}

}