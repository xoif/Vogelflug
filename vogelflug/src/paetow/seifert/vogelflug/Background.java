package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
Bitmap background;
GameView theGameview;


public Background(Bitmap sourceBackground, GameView gameView) {
	this.background = sourceBackground;
	this.theGameview = gameView;
}



public void scroll(){}

public void onDraw(Canvas canvas)
{
scroll();
canvas.drawBitmap(background, 0, 0,null);
}











/* vielleicht kann man ueber die Paint-Klasse beim Bewegen des Hintergrundbilds ne Art 
 * Bewegungsunschaerfe machen um den Fall-Effekt zu intensivieren. Moegliche Kandidaten:
 
paint.setMaskFilter(new BlurMaskFilter(15, Blur.INNER)); 
paint.setMaskFilter(new BlurMaskFilter(15, Blur.OUTER)); -> wohl eher nicht
paint.setMaskFilter(new BlurMaskFilter(15, Blur.SOLID));
paint.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
*/
}
