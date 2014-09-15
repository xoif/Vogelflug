package paetow.seifert.vogelflug;

import java.util.Random;

import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

public class WindBlow {

	private float xValue;
	private float yStart, yTemp, yEnd;
	private Random random;
	private GameView theGameview;
	private Paint windStyle;
	private BlurMaskFilter Filter;
	private LinearGradient Gradient;
	private final int flowSpeed = 60;

	public WindBlow(GameView theGameview) {
		random = new Random();
		windStyle = new Paint();
		this.theGameview = theGameview;
	}

	public void animate() {
		if (yStart > yTemp - 600) {
			yStart -= flowSpeed;
			Gradient = new LinearGradient(xValue, yStart, xValue, yEnd,
					Color.argb(0, 255, 255, 255),
					Color.argb(200, 255, 255, 255), Shader.TileMode.MIRROR);
			windStyle.setShader(Gradient);

		}
		;
		if (yStart <= yTemp - 600 && yEnd >= yStart) {
			yEnd -= flowSpeed * 2;
			Gradient = new LinearGradient(xValue, yStart, xValue, yEnd,
					Color.argb(0, 255, 255, 255),
					Color.argb(200, 255, 255, 255), Shader.TileMode.MIRROR);
			windStyle.setShader(Gradient);
		}

		if (yEnd <= yStart + 30) {
			renew();
		}

	}

	public Paint getWindStyle() {
		return windStyle;
	}

	public float getxValue() {
		return xValue;
	}

	public float getyEnd() {
		return yEnd;
	}

	public float getyStart() {
		return yStart;
	}

	public void renew() {
		Filter = new BlurMaskFilter(5 + random.nextInt(15), Blur.NORMAL);
		windStyle.setStrokeWidth(random.nextInt(10));

		xValue = random.nextInt(theGameview.getWidth()); // width
		yEnd = 100 + random.nextInt(theGameview.getHeight()); // height
		yTemp = yEnd;
		yStart = yTemp - 10;

		windStyle.setMaskFilter(Filter);
		windStyle.setStrokeWidth(6f);
		windStyle.setStyle(Paint.Style.STROKE);
		windStyle.setStrokeJoin(Paint.Join.ROUND);

		Gradient = new LinearGradient(xValue, yStart, xValue, yEnd, Color.argb(
				0, 255, 255, 255), Color.argb(200, 255, 255, 255),
				Shader.TileMode.MIRROR);
		windStyle.setShader(Gradient);

	}

}
