package paetow.seifert.vogelflug;

import android.graphics.Canvas;


public class Wind {

	private WindBlow windBlow;
	private WindBlow windBlow2;

	
	public Wind(GameView theGameView) {
		windBlow = new WindBlow (theGameView);
        windBlow2 = new WindBlow (theGameView);

	}



	public void onDraw(Canvas canvas) {

		windBlow.animate();
	    canvas.drawLine(windBlow.getxValue(), windBlow.getyStart(), windBlow.getxValue(), windBlow.getyEnd(), windBlow.getWindStyle());
		
	    windBlow2.animate();
	    canvas.drawLine(windBlow2.getxValue(), windBlow2.getyStart(), windBlow2.getxValue(), windBlow2.getyEnd(), windBlow2.getWindStyle());

	}

}
