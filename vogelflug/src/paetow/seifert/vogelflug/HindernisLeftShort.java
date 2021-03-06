package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisLeftShort extends HindernisAbstract{

	public HindernisLeftShort(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.bmp=bmp;
		this.position = HindernisManager.LaneChooser.LANE_1;
		this.yPos= 0;
		this.xSpeed = 0;
		this.ySpeed = 10;
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
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
			destine = new Rect(HindernisManager.LaneChooser.getLeft(position) , theGameView.getHeight() - yPos,  
					HindernisManager.LaneChooser.getRight(position),
					theGameView.getHeight() + height - yPos);
			canvas.drawBitmap(bmp, source, destine, null);
			yPos +=ySpeed;
			if(yPos >= theGameView.getHeight()+height){
				draw = false;
				yPos = 0;
			}
		}
		if(KollisionsErkennung.isCollisionDetected(bmp, destine,
				/*	theSprite.getBitmap(),*/ theSprite.getDestine())){
			new CustomTask().execute(-1);
		}
		
	}
	

}
