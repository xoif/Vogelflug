package paetow.seifert.vogelflug;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HindernisManager {

	private GameView theGameView;

	Random Zufall = new Random();
	int change;
	int i = 0;
	int test1, test2;

	private HindernisLeftLong linkslang;
	private HindernisLeftShort linkskurz;
	private HindernisRightLong rechtslang;
	private HindernisRightShort rechtskurz;

	public HindernisManager(Bitmap bmpShortLeft, Bitmap bmpShortRight, Bitmap bmpLongLeft, 
			Bitmap bmpLongRight, GameView theGameView) {
		this.theGameView = theGameView;
		this.test1 = bmpShortLeft.getWidth();
		this.test2 = bmpShortLeft.getHeight();
		
		linkslang = new HindernisLeftLong(bmpLongLeft, theGameView);
		linkskurz = new HindernisLeftShort(bmpShortLeft,theGameView);
		rechtslang = new HindernisRightLong(bmpLongRight, theGameView);
		rechtskurz = new HindernisRightShort(bmpShortRight, theGameView);
	}

	public void change() {
		change = 6; // Zufall.nextInt(10)+1;
		if(change == 1){
			linkslang.setdraw();
		}
		if(change == 2){
			linkskurz.setdraw();
		}
		if(change == 3|| change == 7){
			rechtslang.setdraw();
		}
		if(change == 4|| change == 8){
			rechtskurz.setdraw();
		}
		if(change == 5|| change == 9){
			linkskurz.setdraw();
			rechtslang.setdraw();
		}
		if(change == 6|| change ==10){
			linkslang.setdraw();
			rechtskurz.setdraw();
		}
		
	}

	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas) {
		if (i < 50) {
			i++;
		} else {
			change();
			i = 0;
		}
		Paint farbe = new Paint();
		farbe.setColor(Color.WHITE);
		farbe.setTextSize(80);
		canvas.drawText("testX"+test1+"testY"+(test2), 0, 100, farbe);
		
		linkslang.onDraw(canvas);	
		linkskurz.onDraw(canvas);
		rechtslang.onDraw(canvas);
		rechtskurz.onDraw(canvas);
	
	}

}
