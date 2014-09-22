package paetow.seifert.vogelflug;



import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class GameActivity extends Activity implements OnClickListener{

	private Dialog pauseDialog;
	private Button dialogResume, pauseGame;
	//private static GameLoopThread TheGameLoopThread;
	
	
	
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
        
    }
    
    public void onClick(View v)
	{
	/*	switch (v.getId()){
		case R.id.dialogResume:  resume();
		break;
	
		case R.id.pauseGame: setPause();
		break;
		
		}
		*/
	}

    
    /*
    
    public void setPause ()
    {
    pauseDialog.show();
    // pauseDialog.setTitle("Huhn Huhn Ei Ei Ei");
    TheGameLoopThread = GameView.getTheGameLoopThread();
  //  TheGameLoopThread.setRunning(false);
    //
    }
    
   public void resume()
    {
    	//pauseDialog.hide();
    	pauseDialog.dismiss(); 
    	TheGameLoopThread.setRunning(true); 
    	TheGameLoopThread.run(); 
    }
    
    */

}


	 
 


   

