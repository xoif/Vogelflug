package paetow.seifert.vogelflug;

public class test extends Thread{

	public static void waitForMe(int time)
	{
	try {
		Thread.sleep(time);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	}
}
