package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisRightLong extends HindernisAbstract{

	public HindernisRightLong(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.bmp=bmp;
		this.yPos= 0;
		this.xSpeed = 0;
		this.ySpeed = 10;
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.xPos= theGameView.getWidth() - width;  // Breite der Randwaende
		this.draw = false;
		this.theSprite = theSprite;
		this.destine = new Rect();
	}
	public void setdraw(){
		draw=true;
	}
	
	@SuppressLint("DrawAllocation") public void onDraw(Canvas canvas){
		if(draw == true){
			source = new Rect(0, 0, width, height);
			destine = new Rect(theGameView.getWidth()-width-xPos, theGameView.getHeight()-yPos, theGameView.getWidth() - xPos,
					theGameView.getHeight() + height - yPos);
			canvas.drawBitmap(bmp, source, destine, null);
			yPos +=ySpeed;
			if(yPos >= theGameView.getHeight()+100){
				draw = false;
				yPos = 0;
			}
		}
		if(Rect.intersects(theSprite.getDestine(), destine)){
			new CustomTask().execute(-1);
		}
	}
	

}
