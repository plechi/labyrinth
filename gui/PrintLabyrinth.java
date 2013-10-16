package gui;


import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JPanel;
import controller.Direction;
import controller.IllegalDirectionException;
import controller.LabyrinthField;
import controller.Robot;
import controller.IntStringMap;
import javax.swing.JToolTip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PrintLabyrinth extends JPanel
{
  //HEIGHT and WHITH of a labyrinth cell
  public final static int CELL_HEIGHT = 20;
  public final static int CELL_WIDTH = 20;

  private Image img_way_, img_wall_, img_robot_, img_diamond_;
  private Image img_cellmark0_, img_cellmark1_,img_cellmark2_,img_cellmark3_,img_cellmark_;

  private HashMap brick_images_;
  private IntStringMap mark_array_;
  private IntStringMap state_array_;
  private JToolTip tool_tip_;
  
  private  Robot robot_;
  private  LabyrinthField labyrinth_field_;
  
  public PrintLabyrinth( HashMap brick_images, IntStringMap mark_array, IntStringMap state_array)
  {  
    img_way_ = (Image) brick_images.get("LAB_WAY");
    img_wall_ = (Image) brick_images.get("LAB_WALL");
    img_robot_ = (Image) brick_images.get("LAB_ROBOT");
    img_diamond_ = (Image) brick_images.get("LAB_DIAMOND");
    img_cellmark0_ = (Image) brick_images.get("LAB_CELLMARK0");
    img_cellmark1_ = (Image) brick_images.get("LAB_CELLMARK1");
    img_cellmark2_ = (Image) brick_images.get("LAB_CELLMARK2");
    img_cellmark3_ = (Image) brick_images.get("LAB_CELLMARK3");
	img_cellmark_ = (Image) brick_images.get("LAB_CELLMARK");
    brick_images_ = brick_images;
    mark_array_=mark_array;
    state_array_=state_array;
  }

	public String getToolTipText(MouseEvent e)
	{
		int x = e.getX()/CELL_WIDTH;
		int y = e.getY()/CELL_HEIGHT;
		String mark,state="";
		int dim_x=labyrinth_field_.getNumCols();
		int dim_y=labyrinth_field_.getNumRows();

		if(x<dim_x && y<dim_y)
		{
			if(y==robot_.getPosRow() && x==robot_.getPosCol())
				state=state_array_.getFromInt(robot_.getState());
			mark=mark_array_.getFromInt(labyrinth_field_.getCellMark(y,x));

		if(mark!="" && state!="")
			return ("<html>Markierung: "+mark+"<br>Status: "+state+"</html>");
		else if(mark!="")
			return ("Markierung: "+mark);
		else if(state!="")
			return ("Status: "+state);
			if(mark!="" && state!="")
				return ("<html>Markierung: "+mark+"<br>Status: "+state+"</html>");
			else if(mark!="")
				return ("Markierung: "+mark);
			else if(state!="")
				return ("Status: "+state);
			else
				return "";
		}
		else
			return "";

	}

  public void setLabyrinthField( LabyrinthField labyrinth_field )
  {
      labyrinth_field_ = labyrinth_field;
  }

  
  public void setRobot(Robot robot)
  {
    robot_ = robot;
  }

  public void paintComponent(Graphics g) {
      super.paintComponent(g);
    int hposition, vposition, cols_counter, rows_counter;
    hposition=0;
    vposition=0;
    cols_counter=0;
    rows_counter=0;
		this.setToolTipText("");
    if( (labyrinth_field_ != null) && (robot_ != null)) {
	    while(rows_counter<labyrinth_field_.getNumRows())
	      {
	        while(cols_counter<labyrinth_field_.getNumCols())
	        {
	          if(labyrinth_field_.isWay(rows_counter,cols_counter)==true)
	          {
	              g.drawImage(img_way_, hposition, vposition, this);
	              if(labyrinth_field_.containsDiamond(rows_counter,cols_counter)==true)
	                g.drawImage(img_diamond_, hposition, vposition, this);

	              if(labyrinth_field_.getCellMark(rows_counter,cols_counter) == 0)
		                g.drawImage(img_cellmark0_, hposition, vposition, this);

	              if(labyrinth_field_.getCellMark(rows_counter,cols_counter) == 1)
		                g.drawImage(img_cellmark1_, hposition, vposition, this);

	              if(labyrinth_field_.getCellMark(rows_counter,cols_counter) == 2)
		                g.drawImage(img_cellmark2_, hposition, vposition, this);

	              if(labyrinth_field_.getCellMark(rows_counter,cols_counter) == 3)
		                g.drawImage(img_cellmark3_, hposition, vposition, this);

								if(labyrinth_field_.getCellMark(rows_counter,cols_counter) > 3)
		                g.drawImage(img_cellmark_, hposition, vposition, this);


	          }
	          else
	            g.drawImage(img_wall_, hposition, vposition, this);

	          cols_counter=cols_counter+1;
	          hposition=hposition+CELL_WIDTH;
	        }
	        rows_counter=rows_counter+1;
	        vposition=vposition+CELL_HEIGHT;
	        hposition=0;
	        cols_counter=0;
	      }
	      int robot_vposition = robot_.getPosRow() * CELL_HEIGHT;
	      int robot_hposition = robot_.getPosCol() * CELL_WIDTH;

	      int direction=0;
        try {
            direction = robot_.getCurrentDirection().getDirection();
        }
        catch(IllegalDirectionException e)
        {
            System.out.println("Illegal Direction");
        }


	      String direction_string="UP";
	      switch( direction )
	      {
	        case Direction.DIRECTION_UP: direction_string = "UP"; break;
	        case Direction.DIRECTION_RIGHT: direction_string = "RIGHT";break;
	        case Direction.DIRECTION_DOWN: direction_string = "DOWN";break;
	        case Direction.DIRECTION_LEFT: direction_string = "LEFT";break;
	      }

	      String condition_color="BLANK";

	      switch(robot_.getState() )
	      {
	        case -1 : condition_color = "BLANK"; break;
	        case 0 : condition_color = "GREEN"; break;
	        case 1 : condition_color = "BLUE"; break;
	        case 2 : condition_color = "RED"; break;
	        case 3 : condition_color = "YELLOW"; break;
					default: condition_color = "GRAY"; break;
	      }

	      img_robot_ = (Image) brick_images_.get("LAB_ROBOT_"+condition_color+"_"+direction_string);
	      if(robot_vposition >= 0 && robot_hposition >=0)
	        g.drawImage(img_robot_, robot_hposition, robot_vposition, this);
	    }
  }
}
