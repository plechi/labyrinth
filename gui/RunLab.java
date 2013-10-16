package gui;

public class RunLab implements Runnable
{
	private ControlLevel control_;
	private boolean running_;
	public RunLab (ControlLevel control)
	{
		control_=control;
		running_=true;
	}
	
	public void run()
	{
		while (running_==true)
		{
			control_.runLab();
		}
	}
	public void finish()
	{
		running_=false;
	}
	
}