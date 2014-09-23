package paetow.seifert.vogelflug;



import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends Activity implements OnClickListener{

	private Dialog pauseDialog;
	private Button dialogResume, pauseGame;
	private ImageView gameOverBild;
	private Bitmap bmp;
	private GameLoopThread TheGameLoopThread;
	private TextView pauseText;
	public static GameActivity theGameActivity;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_game);
        
        //Pause Dialog initialisieren
        pauseDialog = new Dialog (this,android.R.style.Theme_Translucent);
        pauseDialog.setContentView(R.layout.pausedialog);
        pauseDialog.hide();
        
        
        //Buttons initialisieren
        dialogResume = (Button)pauseDialog.findViewById(R.id.dialogResume); //ZurueckButton im Dialog initialisieren
        dialogResume.setOnClickListener(this);
        
        pauseGame = (Button) findViewById(R.id.pauseGame); //PauseButton im Menu initialisieren
        pauseGame.setOnClickListener(this);
        
       theGameActivity = this; 
       
       gameOverBild = (ImageView) pauseDialog.findViewById(R.id.Testbild);	
       bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
       pauseText = (TextView) pauseDialog.findViewById(R.id.dialogPauseText);
       

    }
    
    public void onClick(View v)
	{
		switch (v.getId()){
		case R.id.dialogResume:  resume();
		break;
	
		case R.id.pauseGame: setPause(false);
		break;
		
		}
		
	}

    
    
    
    public void setPause (boolean gameOver)
    {
    if (gameOver)
    {
        gameOverBild.setImageBitmap(bmp);	
        pauseText.setText("Game Over");
       dialogResume.setEnabled(false);
       dialogResume.setClickable(false);
       Log.i("Bugtopia", "ich komme hier hin");
   }	
    
    pauseDialog.show();
    TheGameLoopThread = GameView.getTheGameLoopThread();
    TheGameLoopThread.setPaused(true);
    }
    
   public void resume()
    {
   pauseDialog.hide();
   TheGameLoopThread.setPaused(false); 
   
    }

public static GameActivity getTheGameActivity() {
	return theGameActivity;
}
    
   

 
}


	 
 


   

