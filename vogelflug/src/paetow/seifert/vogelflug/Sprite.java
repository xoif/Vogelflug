package paetow.seifert.vogelflug;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;



@SuppressLint("DrawAllocation")
public class Sprite {

	private int x = 6;
	private int y = 6;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;
	private Bitmap bmp;
	private GameView theGameView;
	private final int BMP_COLUMNS = 4;
	private final int BMP_ROWS = 4;
	
	private int spriteRow = 1;
    private int	frameZaehler = 0;

	
	public Sprite(Bitmap bmp, GameView theGameView) {
		super();
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.bmp = bmp;
		this.theGameView = theGameView;

		Random rnd = new Random();

		ySpeed = rnd.nextInt(10);
		xSpeed = rnd.nextInt(10);
	}

	public void bounceOff() {
		if (x <= 5 || x >= theGameView.getWidth() - width) {
			xSpeed *= -1;
			
			if (spriteRow == 0){hoverRight();}
			if (spriteRow == 1){hoverLeft();} //swiped man nach rechts, obwohl man schon am rechten Rand ist, soll der Vogel abprallen und sich drehen
		}

		if (y <= 5 || y >= theGameView.getHeight() - height) {
			ySpeed *= -1;
		}
		x += xSpeed;
		y += ySpeed;
		
		frameZaehler = ++frameZaehler % BMP_COLUMNS;  //Wert des FrameZaehlers Modulo der Spaltenzahl um zwischen 0 und 4 zu bleiben. 
	}

	
	public void moveLeft(){spriteRow = 0;}
	public void moveRight(){spriteRow = 1;}
	public void moveUp(){spriteRow = 2;} 
	public void moveDown(){spriteRow = 3;}
	
	public void hoverLeft(){}
	public void hoverRight(){}
	
	
	
	public void onDraw(Canvas canvas) {
		bounceOff();    //Randkollisionen und FrameCounter
		int sourceX = frameZaehler * width;
		int sourceY = spriteRow * height;
		Rect source = new Rect (sourceX, sourceY, sourceX + width, sourceY + height);  //Rechteck mit den jeweiligen Eckkoordinaten des Sprite-Frames
		Rect destine = new Rect(x, y, x + width, y + height);
		 
		bounceOff();    //Hier drin werden die Aufrufe mitgezaehlt)
		canvas.drawBitmap(bmp, source, destine, null);

	}

}

