package paetow.seifert.vogelflug;

import java.util.Random;

import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

public class Wind {

	private GameView theGameView;
	public float xValue;
	public float yStart, yEnd, yTemp;
	public final int flowSpeed = 60;
	public Paint windStyle;
	public boolean animationEnded;
	public BlurMaskFilter Filter;
	public Random random;

	
	public Wind(GameView theGameView) {
		this.theGameView = theGameView;
		windStyle = new Paint();
		random = new Random();

		animationEnded = true;

		Filter = new BlurMaskFilter(15, Blur.NORMAL);
		windStyle.setMaskFilter(Filter);
		windStyle.setAntiAlias(true);
		windStyle.setStrokeWidth(12f);
		windStyle.setStyle(Paint.Style.STROKE);
		windStyle.setStrokeJoin(Paint.Join.ROUND);

	}

	public void moove() {
		if (animationEnded) {   //erstellt neuen Windstrahl mit neuen Koordinaten
			xValue = random.nextInt(theGameView.getWidth());
			yEnd = 100+ random.nextInt(theGameView.getHeight());
			yTemp = yEnd;
			yStart = yTemp - 10;
			animationEnded = false;
		}

		//TransparenzGradient
		LinearGradient Gradient = new LinearGradient(xValue, yStart, xValue,
				yEnd, Color.argb(200, 255, 50, 50), Color.argb(255, 255, 255,
						255), Shader.TileMode.MIRROR);
		windStyle.setShader(Gradient);

		
		//Wind Animation
		if (yStart > yTemp - 600) {
			yStart -= flowSpeed;
		}
		;
		if (yStart <= yTemp - 600 && yEnd >= yStart) {
			yEnd -= flowSpeed*2;
		}
		if (yEnd <= yStart+30) {
			animationEnded = true;
		}
	}


	
	public void onDraw(Canvas canvas) {

		moove();
		canvas.drawLine(xValue, yStart, xValue, yEnd, windStyle);

	}

}
