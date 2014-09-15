package paetow.seifert.vogelflug;

import android.view.MotionEvent;


public class Controller {

		 float initialX = 0;
		 float initialY = 0;
		 float lastX = 0;
		 float lastY = 0;
		 float deltaX =0;
		 float deltaY=0;
		 final int minDistance = 100;
		 Sprite theSprite;
		 GameView theGameview;
		 
		 Controller(Sprite theSprite, GameView theGameView){
			 
			 super();
			 this.theSprite = theSprite;
			 this.theGameview = theGameView;
		 }
		 
		 public boolean onTouchEvent(MotionEvent event) {

			  // This prevents touchscreen events from flooding the main thread
			  synchronized (event) {
			   try {
			    // Waits 16m
			    event.wait(16);
			    // when user touches the screen
			    
			    switch (event.getAction()) {
			    case MotionEvent.ACTION_DOWN: {
			     // get initial positions
			     initialX = event.getRawX();
			     initialY = event.getRawY();
			     
			    }

			    // when screen is released
			    case MotionEvent.ACTION_UP: {
			     lastX = event.getRawX() ;
			     lastY = event.getRawY() ;
			     }
			    }
			    deltaX=lastX-initialX;
			    deltaY=lastY-initialY;

			    
			    if (Math.abs(deltaX) > Math.abs(minDistance)
			       || Math.abs(deltaY) > Math.abs(minDistance)) {
			      if (Math.abs(deltaX) > Math.abs(deltaY)) {

			       if (deltaX > 0) {
			        // move right
			    	  theSprite.moveRight();
			        
			       } else {
			        // move left
			    	   theSprite.moveLeft();
			       }
			      }

			      else {
			       if (deltaY > 0) {
			        // move down
			    	  // theSprite.moveDown();
			        
			       } else {
			        // move up
			    	  // theSprite.moveUp();
			    	   
			    	   
			        
			       }
			      }
			    }
			    
			    
			    return true;
			   }

			   catch (InterruptedException e) {
			    return true;
			   }
			  }

		 }
		}

