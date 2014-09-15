package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



@SuppressLint({ "WrongCall", "ClickableViewAccessibility" })
public class GameView extends SurfaceView {

	private SurfaceHolder surfaceHolder;
	private Bitmap bmpFly;
	private Bitmap bmpFloat;
	private Bitmap sourceBackground;
	private GameLoopThread theGameLoopThread;
	private Sprite theSprite;
    private Controller theController;
	private Wind theWind, theWind2;
	private Background theBackground;
	
	
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
		
		bmpFly = BitmapFactory.decodeResource(getResources(),R.drawable.alienspritegreen);     //Bitmap einlesen
		bmpFloat = BitmapFactory.decodeResource(getResources(), R.drawable.alienspritered);
		theSprite = new Sprite(bmpFly,bmpFloat,this);
	    theController = new Controller(theSprite,this);
        theWind = new Wind(this);
		sourceBackground = BitmapFactory.decodeResource(getResources(), R.drawable.grand);
		theBackground = new Background(sourceBackground,this);
	}
	
	@SuppressLint("WrongCall")
	protected void onDraw(Canvas canvas)
	{
		theBackground.onDraw(canvas);
		theSprite.onDraw(canvas);
	    theWind.onDraw(canvas);
	   // theWind2.onDraw(canvas); //warum laesst sich keine zweite Instanz erstellen?

	}
	
	
	 
	public boolean onTouchEvent(MotionEvent event) {
    boolean answer = theController.onTouchEvent(event);
    return answer;
	}

	 }	

