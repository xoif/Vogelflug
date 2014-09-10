package paetow.seifert.vogelflug;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;



@SuppressLint("DrawAllocation")
public class Sprite {

	private int x = 6;
	private int y = 6;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;
	private Bitmap bmp;
	private GameView theGameView;

	public Sprite(Bitmap bmp, GameView theGameView) {
		super();
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.bmp = bmp;
		this.theGameView = theGameView;

		Random rnd = new Random();

		ySpeed = rnd.nextInt(10);
		xSpeed = rnd.nextInt(10);
	}

	public void bounceOff() {
		if (x <= 5 || x >= theGameView.getWidth() - width) {
			xSpeed *= -1;
		}

		if (y <= 5 || y >= theGameView.getHeight() - height) {
			ySpeed *= -1;
		}
		x += xSpeed;
		y += ySpeed;
	}

	public void onDraw(Canvas canvas) {
		bounceOff();
		canvas.drawBitmap(bmp, x, y, null);
		String Text = width + " " + height;
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(80);
		
		
		canvas.drawText(Text, x, y, paint);
	}

}

