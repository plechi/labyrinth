package controller;
/*Copyright 2004-2005 Univ.Prof. Dipl.-Ing. Dr.techn. Wolfgang SLANY, 
Andreas Augustin, Sandra Durasiewicz, Bojan Hrnkas, Markus Köberl, 
Bernhard Kornberger, Susanne Schöberl

This file is part of Neptune-Robot-Simulation.

Neptune-Robot-Simulation is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

Neptune-Robot-Simulation is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Neptune-Robot-Simulation; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  US
*/
import java.util.Random;

//----------------------------------------------------------------------
/**
 * class representing a two dimensional labyrinth
 */

public class CreateLabyrinth
{

  /**
	* the number of diamonds distributed in the field
	*/
  protected int num_diamonds_;

  /**
	* the number of way cells distributed in the field
	*/
  protected int num_way_cells_;

  protected int max_row_;
  protected int max_col_;

  /**
	* allow or denay building angles in the Labyrinth
	*/
  protected boolean build_angle_;

	/**
	* allow or denay building a tree in the Labyrinth
	*/
  protected boolean build_tree_;

	/**
	* max number of loops in the Labyrinth
	*/
  protected int build_loop_;

	/**
	* allow or denay building a place
	* There is no way to build a palce in the Labyrinth
	* wirhout to allow to build a loop
	*/
  protected boolean build_places_;

	/**
	* min way lenght
	*/
  protected int min_way_lenght_;

	/**
	* max way lenght
	*/
  protected int max_way_lenght_;

	/**
	* max number of loops for the algorithm
	*/
  protected int max_loops_;

	/**
	* current loops count of the algorithm
	*/
  protected int loops_;

	/**
	* holds the labyrinth field
	*/
  protected LabyrinthField labyrinth_field_;

	/**
	* holds the random generator
	*/
  protected Random random_generator_;

 	/**
	* the length of the way to build
	*/
	protected int way_lenght_;

	/**
	* direction of the way to build
	*/
  protected Direction way_direction_;

	/**
	* new startpoint for the next way
	*/
  protected boolean new_point_;

	/**
	* start coordinates of then next way
	*/
	protected RandomWayCoordinates randomwaycoordinates_;

	/**
	* row coordinate for the algorithm
	*/
  protected int row_;

	/**
	* col coordinate for the algorithm
	*/
  protected int col_;

//------------------------------------------------------------------------------
/**
 * Standard constructor
 * @param labyrinth_field LabyrinthField for modification
 */

  public CreateLabyrinth(LabyrinthField labyrinth_field)
  {
    labyrinth_field_ = labyrinth_field;
    int rows=labyrinth_field_.getNumRows();
    int cols=labyrinth_field_.getNumCols();
    random_generator_ = new Random();
    randomwaycoordinates_ = new RandomWayCoordinates(rows, cols);

    num_diamonds_=0;
    num_way_cells_=0;
    max_row_=rows-1;
    max_col_=cols-1;

    build_angle_=false;
    build_tree_=false;

		build_loop_=0;

    build_places_=false;

    min_way_lenght_=5;
    max_way_lenght_=10;

		max_loops_=rows*cols*10; // abort the algorythm after rows*cols*10

    loops_=0;

    way_lenght_=-1;

    if(labyrinth_field_.isStartpoint()==false)
    {
      labyrinth_field_.convertToStartpoint((rows-1), (cols-1)/2);
      try
      {
        if(labyrinth_field_.isStartDirection()==false)
        {
          Direction direction = new Direction();
          direction.setDirection(Direction.DIRECTION_UP);
          labyrinth_field_.setStartDirection(direction);
        }
          way_direction_ = new Direction();
          way_direction_.setDirection(labyrinth_field_.getStartDirection().getDirection());
      }
      catch(IllegalDirectionException exc)
      {

      }
      row_=labyrinth_field_.getStartpointRow();
      col_=labyrinth_field_.getStartpointCol();
      new_point_=false;
    }
    else if(labyrinth_field_.getNumWayCells()==1 && labyrinth_field_.isStartpoint()==true)
    {
      try
      {
      way_direction_ = new Direction();
      way_direction_.setDirection(labyrinth_field_.getStartDirection().getDirection());
      }
      catch(IllegalDirectionException exc)
      {

      }
      row_=labyrinth_field_.getStartpointRow();
      col_=labyrinth_field_.getStartpointCol();
      new_point_=false;
    }
    else
    {
      row_=-1;
      col_=-1;
      try
      {
        way_direction_=new Direction();
        way_direction_.randomizeNextDirection();
      }
      catch(IllegalDirectionException exc)
      {

      }
      new_point_=true;
    }
  }


//------------------------------------------------------------------------------
/**
 * method called from the outside to set the number
 * of diamonds distributed in this labyrinthfield. It is not checked, whether
 * this number exceeds the maximum allowed number. This has to be done
 * beforehand.
 * @exception IllegalArgumentException thrown if the number of mines
 * is < 0
 */

  public void setNumDiamonds(int num_diamonds)
    throws IllegalArgumentException
  {
    if (num_diamonds < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.DIAMONDS + ">\"" + "must be >= 0"));
    num_diamonds_ = num_diamonds;
  }

//------------------------------------------------------------------------------
/**
 * method called from the outside to set the number
 * of way cells distributed in this labyrinthfield. It is not checked, whether
 * this number exceeds the maximum allowed number. This has to be done
 * beforehand.
 * @exception IllegalArgumentException thrown if the number of way cells
 * is < 0
 */

  public void setNumWayCells(int num_way_cells)
    throws IllegalArgumentException
  {
    if (num_way_cells < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.WAYCELLS + ">\"" + "must be >= 0"));

		num_way_cells_ = num_way_cells;
  }
//----------------------------------------------------------------------------

/**
 * min way length for the labyrinth
 * @param min_way_lenght
 * @exception IllegalArgumentException thrown if min_way_lenght < 0
 */
  public void setMinWayLenght(int min_way_lenght)
    throws IllegalArgumentException
  {
    if (min_way_lenght < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.MINWAYLENGHT + ">\"" + "must be >= 0"));

		min_way_lenght_ = min_way_lenght;
  }

//----------------------------------------------------------------------------

/**
 * max way length for the labyrinth
 * @param max_way_lenght
 * @exception IllegalArgumentException thrown if max_way_lenght < 0
 */
  public void setMaxWayLenght(int max_way_lenght)
    throws IllegalArgumentException
  {
    if (max_way_lenght <= 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.MAXWAYLENGHT + ">\"" + "must be > 0"));

		max_way_lenght_ = max_way_lenght;
  }

//----------------------------------------------------------------------------

/**
 * max number of angles for the labyrinth
 * @param build_angle
 * @exception IllegalArgumentException thrown if build_angle < 0
 */
  public void setBuildAngles(int build_angle)
    throws IllegalArgumentException
  {
    if (build_angle < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.BUILDANGLES + ">\"" + "must be >= 0"));

    if(build_angle>0)
      build_angle_=true;
    else
      build_angle_=false;
  }

//----------------------------------------------------------------------------

/**
 * max number of trees for the labyrinth
 * @param build_tree
 * @exception IllegalArgumentException thrown if build_tree < 0
 */
  public void setBuildTrees(int build_tree)
    throws IllegalArgumentException
  {
    if (build_tree < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.BUILDTREES + ">\"" + "must be >= 0"));

    if(build_tree>0)
      build_tree_=true;
    else
      build_tree_=false;
  }

//----------------------------------------------------------------------------

/**
 * max number of places for the labyrinth
 * there is no way to build a place without to allow to build loops
 * @param build_place
 * @exception IllegalArgumentException thrown if build_place < 0
 */
  public void setBuildPlaces(int build_place)
    throws IllegalArgumentException
  {
    if (build_place < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.BUILDPLACES + ">\"" + "must be >= 0"));

    if(build_place>0)
      build_places_=true;
    else
      build_places_=false;
  }

//----------------------------------------------------------------------------

/**
 * max number of loops for the labyrinth
 * @param build_loop
 * @exception IllegalArgumentException thrown if build_loop < 0
 */
  public void setBuildLoops(int build_loop)
    throws IllegalArgumentException
  {
    if (build_loop < 0)
      throw(new IllegalArgumentException(
        "\"<" + TagName.BUILDLOOPS + ">\"" + "must be >= 0"));

			build_loop_=build_loop;
  }

//------------------------------------------------------------------------------
/**
 * returns a random value between man and max
 * @param min min value of the random value
 * @param max max value of the random value
 * @exception IllegalArgumentException thrown if min > max
 */
  protected int getIntRandomValue(int min, int max)
		throws IllegalArgumentException
	{
	  if(min>max)
			throw(new IllegalArgumentException(
				"\"<"+TagName.MINWAYLENGHT+">\""+"must be <= "+"\"<"+TagName.MAXWAYLENGHT+">\""));

    return min + random_generator_.nextInt(max-min+1);
  }

//------------------------------------------------------------------------------
/**
 * method for calculate a way
 */
  public void calculateWays()
  {
    // set the lenght of the way starting at the startpoint
    way_lenght_=getIntRandomValue(min_way_lenght_, max_way_lenght_);

    if(new_point_==true)
      setNewPoint(); // set new startpoint

    buildWay(); // calculate way

    while(labyrinth_field_.getNumWayCells()<num_way_cells_ && loops_<max_loops_)
    {
      // set the lenght of the way
      way_lenght_=getIntRandomValue(min_way_lenght_, max_way_lenght_);

      // if build_angle_==true allow  angles
      if(build_angle_==true)
      {
        // set the forbidden way direction
        try
        {
          way_direction_.randomizeNextDirection();
        }
        catch(IllegalDirectionException exc)
        {

        }
      }
      buildWay(); // calculate next way
    }
  }

//------------------------------------------------------------------------------
/**
 * method for building a way invoked by calculateWays()
 */
  private void buildWay()
    throws IllegalArgumentException
  {
    if(way_lenght_==-1)
      throw(new IllegalArgumentException(
        "way_lenght_ not set"));

    if(row_==-1)
      throw(new IllegalArgumentException(
        "row_ not set"));
    if(col_==-1)
      throw(new IllegalArgumentException(
        "col_ not set"));

		boolean conditions_array[][]={{true, false},//way_direction_=1  to the top
                                  {false, true},//way_direction_=2  to the right
                                  {true, true},//way_direction_=3  to the botom
                                  {false, false}};//way_direction_=4  to the left

    try
    {
      for(int way_lenght_counter=way_lenght_;way_lenght_counter>0;way_lenght_counter--)
      {
        if(new_point_==true && build_tree_==true)
          setNewPoint();

        int way_direction_index;

        switch (way_direction_.getDirection())
        {
          case Direction.DIRECTION_UP:
            way_direction_index = 0; break;
          case Direction.DIRECTION_RIGHT:
            way_direction_index = 1; break;
          case Direction.DIRECTION_DOWN:
            way_direction_index = 2; break;
          case Direction.DIRECTION_LEFT:
            way_direction_index = 3; break;
          default:
            way_direction_index = 0;
        }

        if(conditions_array[way_direction_index][0]==true) //row_
        {
          if(conditions_array[way_direction_index][1]==true) //++
          {
            if(cellToWay(row_+1,col_,row_+2,col_)==false)
              break;
            row_++;
          }
          else //--
          {
            if(cellToWay(row_-1,col_,row_-2,col_)==false)
              break;
            row_--;
          }
        }
        else //col_
        {
          if(conditions_array[way_direction_index][1]==true) //++
          {
            if(cellToWay(row_,col_+1,row_,col_+2)==false)
              break;
            col_++;
          }
          else //--
          {
            if(cellToWay(row_,col_-1,row_,col_-2)==false)
              break;
            col_--;
          }
        }
      }
    }
    catch(IllegalDirectionException exc)
    {

    }
    loops_++;
  }

//------------------------------------------------------------------------------
/**
 * method for converting a cell to a way invoked by buildWay()
 * @param row row coordinate of the cell to be convertet to a way
 * @param col col coordinate of the cell to be convertet to a way
 * @param row2 row coordinate of the next cell that will be convertet to a way
 * to check if ther will be closed a loop or build a place
 * @param col2 col coordinate of the next cell that will be convertet to a way
 * to check if ther will be closed a loop or build a place
 */
  private boolean cellToWay(int row, int col, int row2, int col2)
  {
    // return false if the cell is not in the field
    if(row<1 ||col<1 || row>max_row_-1 || col>max_col_-1)
      return false;

			boolean is_loop = false;

    // do not allow loops
		if(build_loop_==0)
    {
      if(max_row_<row2|| row2<0 || max_col_<col2 || col2<0)
        return false;
      if(labyrinth_field_.isWay(row2,col2)==true)
      {
        new_point_=true;
        return false;
      }
    }
		else
		{
      if(max_row_<row2|| row2<0 || max_col_<col2 || col2<0)
        return false;
      if(labyrinth_field_.isWay(row2,col2)==true)
      {
        is_loop=true;
      }
		}

    // do not allow places
    if(build_places_==false)
    {
      if(row==row2)
      {
        if(row-1<0 || row+1>max_row_)
				{
				  is_loop = false;
          return false;
				}
        if(labyrinth_field_.isWay(row-1,col)==true)
        {
          new_point_=true;
					is_loop = false;
          return false;
        }
        if(labyrinth_field_.isWay(row+1,col)==true)
        {
          new_point_=true;
					is_loop = false;
          return false;
        }
      }
      else //col==col2
      {
        if(col-1<0 || col+1>max_col_)
				{
					is_loop = false;
          return false;
				}
        if(labyrinth_field_.isWay(row,col-1)==true)
        {
          new_point_=true;
					is_loop = false;
          return false;
        }
        if(labyrinth_field_.isWay(row,col+1)==true)
        {
          new_point_=true;
					is_loop = false;
          return false;
        }
      }
    }

    // get the next cell

    // if the cell is a wall then convert it to a way
    if(labyrinth_field_.isWall(row,col)==true)
    {
      labyrinth_field_.convertToWay(row,col);
    }
    // cell is a way -> buildet loop
    else
    {
      // buildet loop
      new_point_=true;
			is_loop = false;
      loops_++;
      return false;
    }
		if(is_loop == true)
			build_loop_--;
    return true;
  }

//------------------------------------------------------------------------------
/**
 * calculate a new start point
 */
  private void setNewPoint()
  {
    int row=-1;
    int col=-1;
    Cell cell;

    if(randomwaycoordinates_.findRandomWayCoordinates(labyrinth_field_)==true)
    {
      row=randomwaycoordinates_.getRandomWayRowCoordinate();
      col=randomwaycoordinates_.getRandomWayColCoordinate();
      if(labyrinth_field_.isWay(row,col)==true)
      {
        row_=row;
        col_=col;
        way_direction_.randomizeDirection();
        new_point_=false;
      }
    }
  }

//------------------------------------------------------------------------------
/**
 * set diamonds in the labyrinth
 */
  public void setDiamonds()
  {
    int row=-1;
    int col=-1;

    while(labyrinth_field_.getNumDiamonds()<num_diamonds_ && labyrinth_field_.getNumDiamonds()<(labyrinth_field_.getNumWayCells()-1))
    {
      if(randomwaycoordinates_.findRandomWayCoordinates(labyrinth_field_)==true)
      {
        row=randomwaycoordinates_.getRandomWayRowCoordinate();
        col=randomwaycoordinates_.getRandomWayColCoordinate();

        if(labyrinth_field_.containsDiamond(row,col)==false && labyrinth_field_.isWay(row,col)==true && labyrinth_field_.isStartpoint(row,col)==false)
        {
          labyrinth_field_.setDiamond(row, col);
        }
      }
    }
  }

//------------------------------------------------------------------------------
/**
 * returns the labyrinth field
 * @return labyrinth_field_
 */
  public LabyrinthField getLabyrinthField()
  {
    return labyrinth_field_;
  }

//------------------------------------------------------------------------------
/**
 * standard toString method for debugging
 * @return info about the labyrinth field in string-format
 */

  public String toString()
  {
    return("CreateLabyrinth: num_diamonds_ = " + num_diamonds_ +
           ", num_way_cells_ = " + num_way_cells_ +
           ", build_angle_ = " + build_angle_ +
           ", build_tree_ = " + build_tree_ +
           ", build_loop_ = " + build_loop_ +
           ", build_places_ = " + build_places_ +
           ", min_way_lenght_ = " + min_way_lenght_ +
           ", max_way_lenght_ = " + max_way_lenght_ +
           super.toString());
  }

}
