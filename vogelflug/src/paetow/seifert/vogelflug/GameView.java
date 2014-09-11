package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



@SuppressLint("WrongCall")
public class GameView extends SurfaceView {

	private SurfaceHolder surfaceHolder;
	private Bitmap bmp;
	private Bitmap Background;
	private GameLoopThread theGameLoopThread;
	private Sprite theSprite, theSprite2;
	
	
	public GameView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		theGameLoopThread = new GameLoopThread(this);
		surfaceHolder = getHolder();                      //Surface Holder regelt die Oberflaeche und legt fest was bei Veraenderung dieser passiert
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {     
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
		        boolean retry = true;
                theGameLoopThread.setRunning(false);
                while(retry){
                    try {
                        theGameLoopThread.join();
                        retry=false;
                    }catch(InterruptedException e){
 
                    }
				
			}}
			
			@Override
			public void surfaceCreated (SurfaceHolder holder) {
			theGameLoopThread.setRunning(true);
	        theGameLoopThread.start();
	
				
			}
			
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bmp = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);     //Bitmap einlesen
		theSprite = new Sprite(bmp,this);
		theSprite2 = new Sprite(bmp, this);// TODO Auto-generated constructor stub
		Background = BitmapFactory.decodeResource(getResources(), R.drawable.backgrounddesign);
	}
	
	@SuppressLint("WrongCall")
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(Background, 0, 0,null);
		theSprite.onDraw(canvas);
		theSprite2.onDraw(canvas);

	}

	
	
	
	

	
}
