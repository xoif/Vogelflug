package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisRightShort extends HindernisAbstract{

	public HindernisRightShort(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.bmp=bmp;
		this.yPos= 0;
		this.xSpeed = 0;
		this.ySpeed = 10;
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.position = LaneChooser.LANE_4;
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
			destine = new Rect(position.left, theGameView.getHeight() - yPos, position.right,
					theGameView.getHeight()- yPos);
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
