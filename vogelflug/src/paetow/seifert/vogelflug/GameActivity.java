package paetow.seifert.vogelflug;



import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class GameActivity extends Activity implements OnClickListener{

	private Dialog pauseDialog;
	private Button dialogResume;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_game);
        
        //Pause Dialog initialisieren
        pauseDialog = new Dialog (this,android.R.style.Theme_Translucent);
        pauseDialog.setContentView(R.layout.pausedialog);
        pauseDialog.hide();
        
        
        //Buttons initialisieren
        dialogResume = (Button)pauseDialog.findViewById(R.id.dialogResume); //Dialogbutton initialisieren
        dialogResume.setOnClickListener(this);
    }
    
    public void onClick(View v)
	{
		switch (v.getId()){
		case R.id.dialogResume: pauseDialog.hide();break;
		}
	}


}


	 
 


   

