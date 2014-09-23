package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;

abstract class HindernisAbstract {
	
	protected GameView theGameView;
	protected Sprite theSprite;
	protected Rect spritePos;
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


class CustomTask extends AsyncTask<Void, Void, Void> {
	
	protected void onPostExecute(Void param) {
		GameActivity gameActivity = GameActivity.getTheGameActivity();
		gameActivity.setPause(true);
    }

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
	}
}


