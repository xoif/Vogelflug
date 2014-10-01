package paetow.seifert.vogelflug;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class HindernisManager {

	private GameView theGameView;

	Random Zufall = new Random();
	int change;
	int i = 0;

	private HindernisLeftLong linkslang;
	private HindernisLeftShort linkskurz;
	private HindernisRightLong rechtslang;
	private HindernisRightShort rechtskurz;
	private HindernisJumping sprung;
	private static  boolean stop;

	public HindernisManager(Bitmap bmpShortLeft, Bitmap bmpShortRight,
			Bitmap bmpLongLeft, Bitmap bmpLongRight, Bitmap sprung,
			Sprite theSprite, GameView theGameView) {
		this.theGameView = theGameView;
		linkslang = new HindernisLeftLong(bmpLongLeft, theSprite, theGameView);
		linkskurz = new HindernisLeftShort(bmpShortLeft, theSprite, theGameView);
		rechtslang = new HindernisRightLong(bmpLongRight, theSprite,
				theGameView);
		rechtskurz = new HindernisRightShort(bmpShortRight, theSprite,
				theGameView);
		this.sprung = new HindernisJumping(sprung, theSprite, theGameView);
	}

	
	public static void setUnabled(){
		stop = true;
	}
	
	public static void setEnabled(){
		stop = false;
	}
	
	public void change() {
		change = Zufall.nextInt(7)+1;
		if (change == 1) {
			linkslang.setdraw();
			// sprung.setdraw();

		}
		if (change == 2) {
			linkskurz.setdraw();
		//	sprung.setdraw();

		}
		if (change == 3) {
		//	rechtslang.setdraw();
		//	 sprung.setdraw();

		}
		if (change == 4) {
			rechtskurz.setdraw();
			// sprung.setdraw();

		}
		if (change == 5 || change == 7) {
			linkskurz.setdraw();
		//	rechtslang.setdraw();
	//	 sprung.setdraw();

		}
		if (change == 6 || change == 8) {
			linkslang.setdraw();
			rechtskurz.setdraw();
	//		sprung.setdraw();
		}

	}

	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas) {
		if (i < 70) {
			i++;
		} else {
			change();
			i = 0;
		}
		linkslang.onDraw(canvas);
		linkskurz.onDraw(canvas);
		rechtslang.onDraw(canvas);
		rechtskurz.onDraw(canvas);
		sprung.onDraw(canvas);

	}

	public enum LaneChooser {

		LANE_1, LANE_2, LANE_3, LANE_4, LANE_LEFT_LONG, LANE_RIGHT_LONG;

		public static int getLeft(LaneChooser l) {
			switch (l) {
			case LANE_1: {
				return 0;
			}
			case LANE_2: {
				return GameView.getTheWidth() / 4 + 1;
			}
			case LANE_3: {
				return GameView.getTheWidth() / 2 + 1;
			}
			case LANE_4: {
				return GameView.getTheWidth() / 4 * 3 + 1;
			}
			case LANE_LEFT_LONG: {
				return 0;
			}
			case LANE_RIGHT_LONG: {
				return GameView.getTheWidth() / 2 + 1;
			}
			default: {
				return 0;
			}
			}
		}

		public static int getRight(LaneChooser l) {
			switch (l) {
			case LANE_1: {
				return GameView.getTheWidth() / 4;
			}
			case LANE_2: {
				return GameView.getTheWidth() / 2;
			}
			case LANE_3: {
				return GameView.getTheWidth() / 4 * 3;
			}
			case LANE_4: {
				return GameView.getTheWidth();
			}
			case LANE_LEFT_LONG: {
				return GameView.getTheWidth() / 2;
			}
			case LANE_RIGHT_LONG: {
				return GameView.getTheWidth();
			}
			default: {
				return 0;
			}
			
			

			}
		}
	}
}
