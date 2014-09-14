package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
private Bitmap background;
private GameView theGameView;
private int yScroll = 0;
private Rect source, destine;
private boolean bottom = false;


public Background(Bitmap sourceBackground, GameView gameView) {
	this.background = sourceBackground;
	this.theGameView = gameView;
}



public void scroll()
{
source = new Rect(0, yScroll, theGameView.getWidth(), yScroll + theGameView.getHeight());	
destine = new Rect(0, 0, theGameView.getWidth(), theGameView.getHeight());

if (yScroll + theGameView.getHeight() < background.getHeight()){
yScroll++;
}
else {bottom = true;}
	
}

public void onDraw(Canvas canvas)
{
scroll();
canvas.drawBitmap(background, source, destine,null);

}











/* vielleicht kann man ueber die Paint-Klasse beim Bewegen des Hintergrundbilds ne Art 
 * Bewegungsunschaerfe machen um den Fall-Effekt zu intensivieren. Moegliche Kandidaten:
 
paint.setMaskFilter(new BlurMaskFilter(15, Blur.INNER)); 
paint.setMaskFilter(new BlurMaskFilter(15, Blur.OUTER)); -> wohl eher nicht
paint.setMaskFilter(new BlurMaskFilter(15, Blur.SOLID));
paint.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
*/
}
