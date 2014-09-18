package paetow.seifert.vogelflug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HindernisJumping extends HindernisAbstract{
 
 
 private final int BMP_COLUMNS = 8;
 private final int BMP_ROWS = 2;
 private int spriteRow = 0; // 0 fuer Flug von Rechts, 1 fuer Flug von Links
 private int frameZeiger = 0;
 private boolean first;


 public HindernisJumping(Bitmap bmp, GameView theGameView) {
  super(theGameView);
  this.bmp = bmp;
  this.xSpeed = -20;
  this.ySpeed = 25;
  this.width = bmp.getWidth() / BMP_COLUMNS;
  this.height = bmp.getHeight() / BMP_ROWS;
  this.draw = false;
 }

 
 public void setdraw(){
  draw=true;
 }
 
 
 
 
 @Override
 public void onDraw(Canvas canvas) {
  if(first==true){
   xPos= theGameView.getWidth();
   yPos= theGameView.getHeight()/2;
  }
  if(draw == true){
   
   int sourceX = frameZeiger * width;
   int sourceY = spriteRow * height;
   
   
   source = new Rect(sourceX, sourceY, sourceX + width, sourceY
     + height);
   
   destine = new Rect(xPos, theGameView.getHeight() - yPos, width +xPos,
     theGameView.getHeight() + height - yPos);
   
  
frameZeiger = ++frameZeiger % BMP_COLUMNS;
  
   canvas.drawBitmap(bmp, source, destine, null);
   yPos +=ySpeed;
   xPos +=xSpeed; 
   if(xPos >= theGameView.getWidth()+100){draw = false; yPos = 100; xPos = theGameView.getWidth();xSpeed = -20; spriteRow = 0;} 
 }
   if (xPos <= -250){
    draw = false;
    yPos = 100;
    xPos = 0;
    xSpeed = 20;
    spriteRow = 1;

  
  }
 }
}