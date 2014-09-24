package paetow.seifert.vogelflug;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class HindernisManager {

	@SuppressWarnings("unused")
	private GameView theGameView;

	Random Zufall = new Random();
	int change;
	int i = 0;
	int test1, test2;
	
	private HindernisLeftLong linkslang;
	private HindernisLeftShort linkskurz;
	private HindernisRightLong rechtslang;
	private HindernisRightShort rechtskurz;
	private HindernisJumping sprung;

	public HindernisManager(Bitmap bmpShortLeft, Bitmap bmpShortRight, Bitmap bmpLongLeft, 
			Bitmap bmpLongRight, Bitmap sprung, Sprite theSprite, GameView theGameView) {
		this.theGameView = theGameView;
		this.test1 = bmpShortLeft.getWidth();
		this.test2 = bmpShortLeft.getHeight();
		
	//	linkslang = new HindernisLeftLong(bmpLongLeft, theSprite, theGameView);
	//	linkskurz = new HindernisLeftShort(bmpShortLeft,theSprite, theGameView);
		rechtslang = new HindernisRightLong(bmpLongRight, theSprite, theGameView);
		rechtskurz = new HindernisRightShort(bmpShortRight, theSprite, theGameView);
	//	this.sprung = new HindernisJumping (sprung, theSprite, theGameView);
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
		//	linkslang.setdraw();
			rechtskurz.setdraw();
			rechtslang.setdraw();
	//		sprung.setdraw();
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
		
	//	linkslang.onDraw(canvas);	
	//	linkskurz.onDraw(canvas);
		rechtslang.onDraw(canvas);
		rechtskurz.onDraw(canvas);
//		sprung.onDraw(canvas);
	
	}

}
