package gui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.util.HashMap;
import java.awt.event.MouseListener;
import controller.IntStringMap;
import controller.SqlCommunicator;

public class RobotBottomSensorPanel extends JPanel 
{
    public final static int EMPTY_FLOOR = 0;
    public final static int FLOOR_0 = 1;
    public final static int FLOOR_1 = 2;
    public final static int FLOOR_2 = 3;
    public final static int FLOOR_3 = 4;
	public final static int FLOOR_4 = 5;
    public final static int STATE_COUNT = 5;
    private HashMap brick_images_;
    private int internal_state_;
    private Image current_image_;
	private IntStringMap mark_array_;
	private UpdateObject parent_panel_;
	private String name_;
	    
	public RobotBottomSensorPanel(HashMap brick_images, IntStringMap mark_array, UpdateObject parent_panel) 
	{
        super();
		mark_array_=mark_array;
        brick_images_ = brick_images;
        parent_panel_=parent_panel;
        internal_state_ = EMPTY_FLOOR;
        current_image_ = (Image) brick_images_.get("EMPTY_FLOOR");
        this.setToolTipText("Anklicken um den Sensortyp zu ändern.");
        initMouseListener();
    }

    public RobotBottomSensorPanel(HashMap brick_images, IntStringMap mark_array, UpdateObject parent_panel, String name) 
	{
        super();
		mark_array_=mark_array;
        brick_images_ = brick_images;
        parent_panel_=parent_panel;
        name_=name;
        internal_state_ = EMPTY_FLOOR;
        current_image_ = (Image) brick_images_.get("EMPTY_FLOOR");
        this.setToolTipText("Anklicken um den Sensortyp zu ändern.");
        initMouseListener();
    }
    
    public RobotBottomSensorPanel(HashMap brick_images, int init_state, IntStringMap mark_array, UpdateObject parent_panel) 
    {
        super();
		mark_array_=mark_array;
        brick_images_ = brick_images;
        parent_panel_=parent_panel;
        setInternalState(init_state);
        initMouseListener();
    }
    
    public RobotBottomSensorPanel(HashMap brick_images, int init_state, IntStringMap mark_array, UpdateObject parent_panel, String name) 
    {
        super();
		mark_array_=mark_array;
        brick_images_ = brick_images;
        parent_panel_=parent_panel;
        name_=name;
        setInternalState(init_state);
        initMouseListener();
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(current_image_, 0, 0, this);

    }
    
    public void setInternalState(int new_state) 
    {
        if (new_state >= EMPTY_FLOOR)
            internal_state_ = new_state;
		else
        	System.err.println("State must be >=" + EMPTY_FLOOR);	
        paintState();
    }

    public int getInternalState() 
    {
        return internal_state_;
    }
    
    
  private void paintState() 
  {
        switch (internal_state_) 
        {
        case EMPTY_FLOOR:
            current_image_ = (Image) brick_images_.get("EMPTY_FLOOR");
						this.setToolTipText("Anklicken um den Zustand zu ändern.");
            break;
        case FLOOR_0:
            current_image_ = (Image) brick_images_.get("FLOOR_0");
						this.setToolTipText("Markierung: "+mark_array_.getFromInt(internal_state_-1));
            break;
        case FLOOR_1:
            current_image_ = (Image) brick_images_.get("FLOOR_1");
						this.setToolTipText("Markierung: "+mark_array_.getFromInt(internal_state_-1));
            break;
        case FLOOR_2:
            current_image_ = (Image) brick_images_.get("FLOOR_2");
						this.setToolTipText("Markierung: "+mark_array_.getFromInt(internal_state_-1));
            break;
        case FLOOR_3:
            current_image_ = (Image) brick_images_.get("FLOOR_3");
						this.setToolTipText("Markierung: "+mark_array_.getFromInt(internal_state_-1));
            break;
				default:
						current_image_ = (Image) brick_images_.get("FLOOR_4");
						this.setToolTipText("Markierung: "+mark_array_.getFromInt(internal_state_-1));
						break;
        }
        RobotBottomSensorPanel.this.repaint();
    }
    	
	private void initMouseListener() 
	{
        this.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                internal_state_ = (internal_state_ + 1) % (STATE_COUNT);
                
                if(name_!=null)
	            {
		            SqlCommunicator.add_log(name_+" geändert zu "+mark_array_.getFromInt(internal_state_-1), 512);
		        }
		        else
		        {
			        SqlCommunicator.add_log("Zustand geändert zu "+mark_array_.getFromInt(internal_state_-1), 512);
			    }
                paintState();
                parent_panel_.updateParent();
            }
        });	
    }
}