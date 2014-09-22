package paetow.seifert.vogelflug;

import android.annotation.SuppressLint;
import android.graphics.Canvas;



@SuppressLint("WrongCall")
public class GameLoopThread extends Thread{

private GameView theView;
private boolean isRunning = false;
static final long FPS = 20;
Canvas theCanvas;



public GameLoopThread(GameView theView) {
	super();
	this.theView = theView;
}


public void setRunning(boolean isRunning) {
	this.isRunning = isRunning;
}


public void run () 
{
long TPS = 1000 / FPS;
long startTime, sleepTime;

while (true){
while (isRunning)                      //solange der Thread laeuft
{
//Canvas theCanvas = null;                 //ein leeres Canvas-Objekt wird erstellt bzw. wird dessen Inhalt geloescht
startTime = System.currentTimeMillis();
try {
theCanvas = theView.getHolder().lockCanvas();   //der Canvas der GameView-Klasse wird geholt
synchronized (theView.getHolder()) {    //der SurfaceHolder der GameView Klasse wird synchronisiert, damit keine Konflikte mit anderen Threads auftreten
    theView.onDraw(theCanvas);           //der Canvas der GameView-Klasse wird vom Thread bemalt 
    
}
}

finally {
    if (theCanvas != null) {
        theView.getHolder().unlockCanvasAndPost(theCanvas);
    }
}
sleepTime = TPS - (System.currentTimeMillis() - startTime);
try {
    if (sleepTime > 0)
        sleep(sleepTime);
    else
        sleep(10);
} catch (Exception e) {

}
	
}
}
}
}



