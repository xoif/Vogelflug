package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisJumping extends HindernisAbstract {

	
	private final int BMP_COLUMNS = 3;
	private final int BMP_ROWS = 2;
	private int spriteRow = 0; // 0 fuer Flug von Rechts, 1 fuer Flug von Links
	private int frameZeiger = 0;
	private boolean first;
	private int xPos;
	private int vorzeichen = -1;
	
	
	

	public HindernisJumping(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.bmp = bmp;
		this.xSpeed = 0;    //-30
		this.ySpeed = -10; //-15;
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
			xPos = theGameView.getWidth() - bmp.getWidth();
			yPos = theGameView.getHeight();
		}
		if (draw == true) {

			int sourceX = frameZeiger * width;
			int sourceY = spriteRow * height;

			source = new Rect(sourceX, sourceY, sourceX + width, sourceY
					+ height);

			destine = new Rect(xPos, yPos, width
					+ xPos,  yPos-height);

			 if (frameZeiger == BMP_COLUMNS || frameZeiger == 0) {vorzeichen *= -1;}
			 frameZeiger += vorzeichen; 

			canvas.drawBitmap(bmp, source, destine, null);
		
			yPos += ySpeed;
			xPos += xSpeed;
			
			if (yPos <= theGameView.getHeight() /3 && spriteRow == 1){ySpeed = -15;xSpeed = 30;}
			if (yPos <= theGameView.getHeight() /3 && spriteRow == 0){ySpeed = -15;xSpeed = -30;}
			
			if (xPos >= theGameView.getWidth() + 100) {
				draw = false;
				yPos = 0;
				xPos = theGameView.getWidth()-bmp.getWidth();
				xSpeed = 0;
				ySpeed = 10;
				spriteRow = 0;
			}
		}
		if (xPos <= -250) {
			draw = false;
			yPos = 0;
			xPos = 0;
			xSpeed = 0;
			ySpeed = 10;
			spriteRow = 1;

		}

		if(KollisionsErkennung.isCollisionDetected(bmp, destine,
				theSprite.getBitmap(), theSprite.getDestine())){
			new CustomTask().execute(-1);
		}
	}
}