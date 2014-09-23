package paetow.seifert.vogelflug;



import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends Activity implements OnClickListener{

	private static Dialog pauseDialog;
	private static Button dialogResume, pauseGame;
	private static ImageView gameOverBild;
	private static Bitmap bmp;
	private static GameLoopThread TheGameLoopThread;
	private static TextView pauseText;
	
	
	
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
        
        //im Dialog eingeblendetes ImageView initialisieren
        
        gameOverBild = (ImageView) findViewById(R.id.Testbild);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
        pauseText = (TextView) findViewById(R.id.dialogPauseText);

    }
    
    public void onClick(View v)
	{
		switch (v.getId()){
		case R.id.dialogResume:  resume();
		break;
	
		case R.id.pauseGame: setPause();
		break;
		
		}
		
	}

    
    
    
    public static void setPause ()
    {
    if (HindernisAbstract.isGameOver()){gameOverBild.setImageBitmap(bmp);pauseText.setText("Game Over");dialogResume.setEnabled(false);dialogResume.setClickable(false);}	
    pauseDialog.show();
    TheGameLoopThread = GameView.getTheGameLoopThread();
    TheGameLoopThread.setPaused(true);
    }
    
   public void resume()
    {
   pauseDialog.hide();
   TheGameLoopThread.setPaused(false); 
   
    }
    
    

}


	 
 


   

