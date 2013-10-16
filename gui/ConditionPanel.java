package gui;

import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import controller.Condition;
import controller.IntStringMap;
import controller.SqlCommunicator;
public class ConditionPanel extends JPanel implements UpdateObject
{
	private RobotEnviromentSensorPanel left_sensor_;
    private RobotEnviromentSensorPanel right_sensor_;
    private RobotEnviromentSensorPanel ahead_sensor_;
    private RobotBottomSensorPanel bottom_sensor_;   
    private RobotStatePanel state_panel_;
    private RulePanel parent_panel_;
	private HashMap brick_images_;
	private int id_;
	
	public void updateParent()
	{
		parent_panel_.conditionUpdated();
	}
	
	public ConditionPanel(HashMap brick_images, IntStringMap mark_array, IntStringMap state_array, RulePanel parent_panel, int id) 
    {	    
		super();
        brick_images_ = brick_images;
        parent_panel_=parent_panel;
        id_=id;
        left_sensor_ = new RobotEnviromentSensorPanel(brick_images_, this, "Regel "+id_+" Bedingung: Sensor links");
        right_sensor_ = new RobotEnviromentSensorPanel(brick_images_, this, "Regel "+id_+" Bedingung: Sensor rechts");
        ahead_sensor_ = new RobotEnviromentSensorPanel(brick_images_, this, "Regel "+id_+" Bedingung: Sensor vorne");
        bottom_sensor_ = new RobotBottomSensorPanel(brick_images_, mark_array, this, "Regel "+id_+" Bedingung: Boden Sensor");
        state_panel_ = new RobotStatePanel(brick_images_,state_array, false, this, "Regel "+id_+" Bedingung: Roboterstatus");
        
        initComponents();   
	}

	public ConditionPanel(HashMap brick_images, IntStringMap mark_array, IntStringMap state_array, Condition condition, RulePanel parent_panel, int id) 
    {	    
		super();
        brick_images_ = brick_images;
        parent_panel_=parent_panel;
        id_=id;
        int tmp;
        if (condition.isWallLeft() == true)
        	tmp = RobotEnviromentSensorPanel.WALL;
        else
         	tmp = RobotEnviromentSensorPanel.FREE;	
        left_sensor_ = new RobotEnviromentSensorPanel(brick_images_, tmp, this, "Regel "+id_+" Bedingung: Sensor links");
        if (condition.isWallRight() == true)
        	tmp = RobotEnviromentSensorPanel.WALL;
        else
         	tmp = RobotEnviromentSensorPanel.FREE;	
        right_sensor_ = new RobotEnviromentSensorPanel(brick_images_, tmp, this, "Regel "+id_+" Bedingung: Sensor rechts");
        if (condition.isWallAhead() == true)
        	tmp = RobotEnviromentSensorPanel.WALL;
        else
         	tmp = RobotEnviromentSensorPanel.FREE;	
        ahead_sensor_ = new RobotEnviromentSensorPanel(brick_images_, tmp, this, "Regel "+id_+" Bedingung: Sensor vorne");       
        bottom_sensor_ = new RobotBottomSensorPanel(brick_images_, condition.getCellMark()+1, mark_array, this, "Regel "+id_+" Bedingung: Boden Sensor");        
        state_panel_ = new RobotStatePanel(brick_images_,condition.getState()+1, state_array, false, this, "Regel "+id_+" Bedingung: Roboterstatus");
        initComponents();   
	}
	
	private void initComponents() 
	{
        JLabel top_label = new JLabel();
        this.setLayout(null);
        this.add(top_label);
        this.add(left_sensor_);
        this.add(right_sensor_);
        this.add(ahead_sensor_);
        this.add(bottom_sensor_);
        this.add(state_panel_); 
        top_label.setText("Sensorregel:");
        top_label.setBounds(3, 0, 150, 15);        
        left_sensor_.setBounds(8, 38, 20, 20);
        ahead_sensor_.setBounds(30, 16, 20, 20);
        right_sensor_.setBounds(52, 38, 20, 20);
        bottom_sensor_.setBounds(30, 38, 20, 20);
        state_panel_.setBounds(25, 60, 30, 15);

        setBackGround(Color.GRAY);
        repaint();
    }
    
	public Condition getCondition() 
    {        
    	boolean ahead_sensor_bool, left_sensor_bool, right_sensor_bool;      
    	if (ahead_sensor_.getInternalState() == RobotEnviromentSensorPanel.FREE)
        	ahead_sensor_bool = false;
        else
         	ahead_sensor_bool = true;
       	if (left_sensor_.getInternalState() == RobotEnviromentSensorPanel.FREE)
         	left_sensor_bool = false;
    	else
         	left_sensor_bool = true;
    	if (right_sensor_.getInternalState() == RobotEnviromentSensorPanel.FREE)
           	right_sensor_bool = false;
        else
          	right_sensor_bool = true;
    
        Condition ret_condition = new Condition(state_panel_.getInternalState()-1,
                bottom_sensor_.getInternalState()-1, left_sensor_bool, right_sensor_bool, ahead_sensor_bool);
        return ret_condition;
    }
    
    public void setBackGround(Color condition)
    {
        this.setBackground(condition);
	}
    
   
	public void updateCondition(Condition condition)
	{
		int tmp;
        if (condition.isWallLeft() == true)
        	tmp = RobotEnviromentSensorPanel.WALL;
        else
         	tmp = RobotEnviromentSensorPanel.FREE;	
        left_sensor_.setInternalState(tmp);
        if (condition.isWallRight() == true)
        	tmp = RobotEnviromentSensorPanel.WALL;
        else
         	tmp = RobotEnviromentSensorPanel.FREE;	
        right_sensor_.setInternalState(tmp);
        if (condition.isWallAhead() == true)
        	tmp = RobotEnviromentSensorPanel.WALL;
        else
         	tmp = RobotEnviromentSensorPanel.FREE;	
        ahead_sensor_.setInternalState(tmp);        
		bottom_sensor_.setInternalState(condition.getCellMark()+1);
		state_panel_.setInternalState(condition.getState()+1);
	}
}
