package paetow.seifert.vogelflug;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends Activity implements OnClickListener{

	private Dialog pauseDialog;
	private Button dialogResume, pauseGame, newGame;
	private Drawable bmp;
	private GameLoopThread TheGameLoopThread;
	private TextView pauseText, gameScore, gameOverBild;
	public static GameActivity theGameActivity;
    private int theScore;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_game);
        
        theGameActivity = this; 
        
        //Pause Dialog initialisieren
        pauseDialog = new Dialog (this,android.R.style.Theme_Translucent);
        pauseDialog.setContentView(R.layout.pausedialog);
        pauseDialog.hide();
        pauseText = (TextView) pauseDialog.findViewById(R.id.dialogPauseText);
        
        
        //Buttons initialisieren
        dialogResume = (Button)pauseDialog.findViewById(R.id.dialogResume); //ZurueckButton im Dialog initialisieren
        dialogResume.setOnClickListener(this);
        
        pauseGame = (Button) findViewById(R.id.pauseGame); //PauseButton im Menu initialisieren
        pauseGame.setOnClickListener(this);
        
        newGame = (Button) pauseDialog.findViewById(R.id.newGame);
        newGame.setOnClickListener(this);

       //GameOver Inhalte initialisieren
       gameOverBild = (TextView) pauseDialog.findViewById(R.id.Testbild);	
       bmp =  getResources().getDrawable(R.drawable.gameover);
      
       
       //Spielstandinhalte initialisieren
       gameScore = (TextView)findViewById(R.id.Score);
       

    }
    
    public void onClick(View v)
	{
		switch (v.getId()){
		case R.id.dialogResume:  resume();
		break;
	
		case R.id.pauseGame: setPause(false);
		break;
		
		case R.id.newGame: Intent in = new Intent(this,GameActivity.class);
    	startActivity(in);finish();
	    break; 	
		}
		
	}

    
    
    
    public void setPause (boolean gameOver)
    {
    if (gameOver)
    {
        gameOverBild.setBackground(bmp);
        pauseText.setText("Game Over");
       dialogResume.setEnabled(false);
       dialogResume.setClickable(false);
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

public int getTheScore() {
	return theScore;
}

public void setTheScore(int theScore) {
	this.theScore = theScore;
	String Ausgabe = ""+theScore;
	Log.i("Bugtopia", Ausgabe);
	gameScore.setText(Ausgabe);
	gameOverBild.setText(Ausgabe);
}

 
}


	 
 


   

