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

	public static GameLoopThread getTheGameLoopThread() {
		return theGameLoopThread;
	}
	public static int getTheWidth() {
		return theWidth;
	}
	private SurfaceHolder surfaceHolder;
	private Bitmap bmpFly;
	private Bitmap bmpFloat;
	private Bitmap sourceBackground;
	private Bitmap sourceWallLeft, sourceWallRight;
	private Bitmap branchShortLeft, branchShortRight, branchLongLeft, branchLongRight, oachKatzl;
	private static GameLoopThread theGameLoopThread;
	private Sprite theSprite;
	private Controller theController;
	private Wind theWind;
	private Background theBackground;
	@SuppressWarnings("unused")
	private Wand theWand;

	private HindernisManager theHindernis;

	private static int theWidth;

	public GameView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		theGameLoopThread = new GameLoopThread(this);
		surfaceHolder = getHolder(); // Surface Holder regelt die Oberflaeche
										// und legt fest was bei Veraenderung
										// dieser passiert
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				
				
				// TODO Auto-generated method stub

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				theGameLoopThread.setRunning(true);
				theGameLoopThread.start();
				theWidth = getMeasuredWidth();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				theGameLoopThread.setRunning(false);
				while (retry) {
					try {
						theGameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {

					}
				}
				bmpFly.recycle();
				bmpFloat.recycle();
				sourceBackground.recycle();
				sourceWallLeft.recycle();
				sourceWallRight.recycle();
				branchShortLeft.recycle();
				branchShortRight.recycle();
				branchLongLeft.recycle();
				branchLongRight.recycle();
				oachKatzl.recycle();				
			}
		});

		bmpFly = BitmapFactory.decodeResource(getResources(),
				R.drawable.alienspritegreen); // Bitmap einlesen
		bmpFloat = BitmapFactory.decodeResource(getResources(),
				R.drawable.alienspritered);
		theSprite = new Sprite(bmpFly, bmpFloat, this);

		theController = new Controller(theSprite, this);

		theWind = new Wind(this);
		theBackground = new Background(this);
		

		sourceWallLeft = BitmapFactory.decodeResource(getResources(), R.drawable.leftwall);
		sourceWallRight = BitmapFactory.decodeResource(getResources(), R.drawable.rightwall);
		theWand = new Wand(sourceWallLeft, sourceWallRight, this);

		branchShortLeft = BitmapFactory.decodeResource(getResources(), R.drawable.leftshort);
		branchShortRight = BitmapFactory.decodeResource(getResources(), R.drawable.rightshort);

		branchLongLeft = BitmapFactory.decodeResource(getResources(), R.drawable.leftlong);
		branchLongRight = BitmapFactory.decodeResource(getResources(), R.drawable.rightlong);
		oachKatzl = BitmapFactory.decodeResource(getResources(), R.drawable.oachkatzl);
		
		theHindernis = new HindernisManager(branchShortLeft,branchShortRight, branchLongLeft, 
				branchLongRight, oachKatzl, theSprite, this);

	}

	public Bitmap getBackground (int level)
	{
	if (level == 1){
		sourceBackground = BitmapFactory.decodeResource(getResources(),R.drawable.wolken);return sourceBackground;}
	if(level ==2){
			sourceBackground = BitmapFactory.decodeResource(getResources(),R.drawable.wald);return sourceBackground;
		}	
	else {sourceBackground = BitmapFactory.decodeResource(getResources(),R.drawable.buschwerk);return sourceBackground;}	
	}

	@Override
	@SuppressLint("WrongCall")
	protected void onDraw(Canvas canvas) {
		theBackground.onDraw(canvas);
		theSprite.onDraw(canvas);
		theWind.onDraw(canvas);
		//theWand.onDraw(canvas);
		theHindernis.onDraw(canvas);
		
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean answer = theController.onTouchEvent(event);
		return answer;
	}
		
		
	}
	

	

