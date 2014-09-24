package paetow.seifert.vogelflug;


public enum LaneRect {
	
	
LANE_1(0,GameView.getTheWidth()/4),
LANE_2(GameView.getTheWidth()/4 + 1, GameView.getTheWidth()/2),
LANE_3(GameView.getTheWidth()/2 + 1, GameView.getTheWidth()/4 * 3),
LANE_4(GameView.getTheWidth()/4 * 3 + 1, GameView.getTheWidth());


private final int left;
private final int right;



private LaneRect (int left, int right){
	this.left = left;
	this.right = right;
	    }


public static LaneRect getEnumByValue(int value) {
	switch (value) {
		case 0:   	return LANE_1;
		case 1:   	return LANE_2;
		case 2:   	return LANE_3;
		case 3:   	return LANE_4;
		default:  	return null;
	}
}
		
}

