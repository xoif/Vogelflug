package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisJumping extends HindernisAbstract{
	
	
	private final int BMP_COLUMNS = 8;
	private final int BMP_ROWS = 2;
	private int spriteRow = 0; // 0 fuer Flug von Rechts, 1 fuer Flug von Links
	private int frameZeiger = 0;
	

	public HindernisJumping(Bitmap bmp, GameView theGameView) {
		super(theGameView);
		this.bmp = bmp;
		this.xPos= 50;
		this.yPos= 0;
		this.xSpeed = 0;
		this.ySpeed = 10;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.draw = false;
	}

	
	public void setdraw(){
		draw=true;
	}
	
	
	
	
	@Override
	public void onDraw(Canvas canvas) {
		
		if (spriteRow == 0){xPos = 1200;}
		else {xPos = -3;}
		
		frameZeiger = (++frameZeiger) % BMP_COLUMNS;
		
		
		int sourceX = frameZeiger * width;
		int sourceY = spriteRow * height;
				
		if(draw == true){
			source = new Rect(sourceX, sourceY, sourceX + width, sourceY
					+ height);
			
			destine = new Rect(xPos, theGameView.getHeight() - yPos,  theGameView.getWidth()/8,
					theGameView.getHeight() + height - yPos);
			canvas.drawBitmap(bmp, source, destine, null);
			yPos +=ySpeed;
			if(yPos >= theGameView.getHeight()+100){
				draw = false;
				yPos = 0;
	}
		}
	}
}

