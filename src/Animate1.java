
public class Animate1 implements Runnable {

	BlockBreakerPanel bp;
	
	Animate1(BlockBreakerPanel b){
		bp = b;
	}
	public void run(){
		while(true){
			bp.update();
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
