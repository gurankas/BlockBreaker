
public class Animate2 implements Runnable {

	BlockBreakerPanel bp;
	
	Animate2(BlockBreakerPanel b){
		bp = b;
	}
	public void run(){
		while(true){
			bp.update();
			try{
			Thread.sleep(5);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
