package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Canvas;


@SuppressLint("WrongCall")
public class GameLoopThread extends Thread {

	private GameView theView;
	private boolean isRunning = false, isPaused = false;
	static final long FPS = 20;
	Canvas theCanvas;
	private static int gameScore;


	public GameLoopThread(GameView theView) {
		super();
		this.theView = theView;
	}

	@Override
	public void run() {
		long TPS = 1000 / FPS;
		long startTime, sleepTime;
	
		
		while (isRunning) {
			while (!isPaused) {
			
				// Canvas theCanvas = null; //ein leeres Canvas-Objekt wird
				// erstellt bzw. wird dessen Inhalt geloescht
				gameScore ++; new CustomTask().execute(gameScore);
				
				startTime = System.currentTimeMillis();
				try {
					theCanvas = theView.getHolder().lockCanvas(); // der Canvas
																	// der
																	// GameView-Klasse
																	// wird
																	// geholt
					synchronized (theView.getHolder()) { // der SurfaceHolder
															// der GameView
															// Klasse wird
															// synchronisiert,
															// damit keine
															// Konflikte mit
															// anderen Threads
															// auftreten
						theView.onDraw(theCanvas); // der Canvas der
													// GameView-Klasse wird vom
													// Thread bemalt

					}
				}

				finally {
					if (theCanvas != null) {
						theView.getHolder().unlockCanvasAndPost(theCanvas);
					}
				}
				sleepTime = TPS - (System.currentTimeMillis() - startTime);
				try {
					if (sleepTime > 0) {
						sleep(sleepTime);
					} else {
						sleep(10);
					}
				} catch (Exception e) {

				}

			
				}
		}
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public static int getGameScore() {
		return gameScore;
	}
	public static void setGameScore(int x) {
		gameScore = x;
	}


	
	
	
	
}
