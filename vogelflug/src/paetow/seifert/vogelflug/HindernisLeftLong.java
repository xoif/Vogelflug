package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisLeftLong extends HindernisAbstract{
	
	private final int BMP_COLUMNS = 4;
	private int frameZeiger = 0;
	private int vorzeichen = -1; //positiv oder negativ

	public HindernisLeftLong(Bitmap bmp, Sprite theSprite, GameView theGameView) {
		super(theGameView);
		this.theSprite = theSprite;
		this.bmp =bmp;
		this.yPos = 0;
		this.xSpeed = 0;
		this.ySpeed = 10;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight();
		this.draw = false;
		this.destine = new Rect();
		this.position = HindernisManager.LaneChooser.LANE_LEFT_LONG;
		
		
	}
	
	public void setdraw(){
		draw=true;
	}
	
	@SuppressLint("WrongCall") public void onDraw(Canvas canvas){
		
		int sourceX = frameZeiger * width;
		
		if(draw == true){
			source = new Rect(sourceX, 0, sourceX+ width, height);
			destine = new Rect(HindernisManager.LaneChooser.getLeft(position) , theGameView.getHeight() - yPos, 
					HindernisManager.LaneChooser.getRight(position),
					theGameView.getHeight() + height - yPos);
		

			 if (frameZeiger == BMP_COLUMNS || frameZeiger == 0) {vorzeichen *= -1;}
			 frameZeiger += vorzeichen; 
			 
			canvas.drawBitmap(bmp, source, destine, null);
			yPos += ySpeed;
			if(yPos >= theGameView.getHeight()+height){
				draw = false;
				yPos = 0;
			}	
		}
		
		if(KollisionsErkennung.isCollisionDetected(bmp, destine,
				theSprite.getBitmap(), theSprite.getDestine())){
			new CustomTask().execute(-1);
		}
			
		
	}
	

}