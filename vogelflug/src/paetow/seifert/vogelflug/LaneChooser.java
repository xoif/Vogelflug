package paetow.seifert.vogelflug;


	

public enum LaneChooser {
	
LANE_1(0,GameView.getTheWidth()/4),
LANE_2(GameView.getTheWidth()/4 + 1, GameView.getTheWidth()/2),
LANE_3(GameView.getTheWidth()/2 + 1, GameView.getTheWidth()/4 * 3),
LANE_4(GameView.getTheWidth()/4 * 3 + 1, GameView.getTheWidth()),
LANE_LEFT_LONG(0, GameView.getTheWidth()/2),
LANE_RIGHT_LONG(GameView.getTheWidth()/2 + 1,GameView.getTheWidth());


protected int left;
protected int right;

private LaneChooser (int posiLeft, int posiRight){
	this.left = posiLeft;
	this.right = posiRight;
	    }

}


