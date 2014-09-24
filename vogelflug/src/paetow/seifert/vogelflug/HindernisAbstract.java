package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.widget.TextView;


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

	public HindernisAbstract(GameView theGameView) {

		this.theGameView = theGameView;
	}

	public abstract void onDraw(Canvas canvas);

}



/*Generics: 
1. Referenzen, die an doInBackground weitergegeben werden
2. Referenzen, die an onProgressUpdate weitergegeben werden
3. Referenzen, die von doInBackground an onPostExecute() (=der Mainthread) weitergegeben werden.
*/

class CustomTask extends AsyncTask<Integer, Integer, Boolean> {

	private GameActivity gameActivity;
	
	@Override
	protected Boolean doInBackground(Integer... gameScore) {
	
		if (gameScore[0] == -1){return true;}
		else {
			int Score = gameScore [0];
		publishProgress(Score);
		
		return false;}
	}

	protected void onPostExecute(Boolean gameOver) {
		if (gameOver) {
		gameActivity = GameActivity.getTheGameActivity();	
		gameActivity.setPause(true);
		}


	}

	@Override
	protected void onProgressUpdate(Integer... Score) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(Score);
		gameActivity = GameActivity.getTheGameActivity();
		gameActivity.setTheScore(Score[0]);

	}
}

