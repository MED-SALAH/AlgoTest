package recognizer;


public class AnalyseModel implements Runnable{
	public AnalyseModel(String name)
	{
		this.name = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i =0;i<10;i++)
		{
			System.out.println("Hi, My Name Is "+name);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	String name;
	}
