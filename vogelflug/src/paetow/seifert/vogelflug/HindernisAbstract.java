package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

abstract class HindernisAbstract {
	
	protected GameView theGameView;
	protected Bitmap bmp, wall;
	protected int xPos, yPos;
	protected int xSpeed, ySpeed;
	protected int width, height;
	protected boolean draw;
	protected Rect source;
	protected Rect destine;

	public HindernisAbstract(GameView theGameView){
		
		this.theGameView = theGameView;		
	}
	
	public abstract void onDraw(Canvas canvas);
}
