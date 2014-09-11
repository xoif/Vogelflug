package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;



@SuppressLint("DrawAllocation")
public class Sprite {

	private int x = 6;
	private int y = 6;
	private int xSpeed = 6;
	private int width;
	private int height;
	private Bitmap bmp;
	private GameView theGameView;
	private final int BMP_COLUMNS = 4;
	private final int BMP_ROWS = 4;
	
	private int spriteRow = 0;     // von 0 - 3 fuer die jeweiligen Richtungssprites
    private int	frameZaehler = 0;

	
	public Sprite(Bitmap bmp, GameView theGameView) {
		super();
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.bmp = bmp;
		this.theGameView = theGameView;

	}

	public void bounceOff() {
		if (x <= 5 || x >= theGameView.getWidth() - width) {
			xSpeed *= -1;
			
			if (spriteRow == 0){hoverRight();}
			if (spriteRow == 1){hoverLeft();} //swiped man nach rechts, obwohl man schon am rechten Rand ist, soll der Vogel abprallen und sich drehen
		}
		x += xSpeed;
		frameZaehler = (++frameZaehler) % BMP_COLUMNS;  //Wert des FrameZaehlers Modulo der Spaltenzahl um zwischen 0 und 4 zu bleiben. 
	}

	
	public void moveLeft(){spriteRow = 1; xSpeed = 6;}
	public void moveRight(){spriteRow = 0; xSpeed = -6;}
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
		 
		canvas.drawBitmap(bmp, source, destine, null);

	}

}

