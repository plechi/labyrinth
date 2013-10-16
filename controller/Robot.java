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
/**
 * class representing a Robot.
 */
public class Robot
{
  private int position_row_;
  private int position_col_;
  private Direction start_direction_;
  private Direction current_direction_;
  private Direction last_direction_;
  private int state_;
  private int num_taken_things_;
  private int step_count_;

//-----------------------------------------------------------------------------
/**
 * standard constructor
 */
  public Robot()
  {
	num_taken_things_=0;
	step_count_=0;
    position_row_ = -1;
    position_col_ = -1;
    start_direction_ = new Direction();
    current_direction_= new Direction();
    state_ = -1;
  }

//-----------------------------------------------------------------------------
/**
 * method returns the mark new_area_
 * @return info about the way the robot has taken
 * when it returns false the robot wasn't on this cell before
 */
   public int getState()
  {
    return state_;
  }

//-----------------------------------------------------------------------------
/**
 * return the row position of the robot
 * @return row
 */
  public int getPosRow()
  {
    return position_row_;
  }

//-----------------------------------------------------------------------------
/**
 * return the col position of the robot
 * @return col
 */
  public int getPosCol()
  {
    return position_col_;
  }

//-----------------------------------------------------------------------------
/**
 * return the current direction
 * @return current_direction
 */
  public Direction getStartDirection()
  {
    return start_direction_;
  }

//-----------------------------------------------------------------------------
/**
 * return the current direction
 * @return current_direction
 */
  public Direction getCurrentDirection()
  {
    return current_direction_;
  }

//-----------------------------------------------------------------------------
/**
 * restore the last direction
 */
  public void restoreLastDirection()
  {
    current_direction_= new Direction(last_direction_);
  }

//-----------------------------------------------------------------------------
/**
 * save the current direction
 * @param state
 */
  private void saveCurrentDirection()
  {
     last_direction_= new Direction(current_direction_);
  }

//-----------------------------------------------------------------------------
/**
 * set int value of the robot state
 * @param state
 * @exception IllegalArgumentException thrown if the state is <-1
 */
  public void setState(int state)
    throws IllegalArgumentException
  {
    if(state<-1)
     throw(new IllegalArgumentException(
       "state must be >= -1"));
    state_ = state;
  }

//-----------------------------------------------------------------------------
/**
 * set the robot at a definite position
 * @param row row position
 * @param col col position
 */
  public void setPos(int row, int col)
  {
    position_row_ = row;
    position_col_ = col;
  }

//-----------------------------------------------------------------------------
/**
 * set a start direction for the robot.
 * @param direction
 * @exception IllegalDirectionException thrown if the direction isn't valid.
 */
  public void setStartDirection(Direction start_direction)
    throws IllegalDirectionException
  {
    start_direction.getDirection();    // wirft Exception
//     start_direction_= start_direction; //ersetzt alte direction nicht
    start_direction_= new Direction(start_direction);
    current_direction_ = start_direction_;
  }

//-----------------------------------------------------------------------------
/**
 * internal method to move a robot one cell to the given direction.
 * @param direction
 * @exception IllegalDirectionException thrown if the direction isn't valid.
 */

  protected void moveInDirection(Direction direction)
    throws IllegalDirectionException
  {
    switch (direction.getDirection())
    {
      case Direction.DIRECTION_UP:
        position_row_ -= 1;
        break;
      case Direction.DIRECTION_DOWN:
        position_row_ += 1;
        break;
      case Direction.DIRECTION_RIGHT:
        position_col_ += 1;
        break;
      case Direction.DIRECTION_LEFT:
        position_col_ -= 1;
        break;
      default:
        break;
    }
    step_count_++;
  }

//-----------------------------------------------------------------------------
/**
 * method to move a robot one cell forward (relative to his current direction)
 * @exception IllegalDirectionException thrown if the direction isn't valid.
 */
  public void moveForward()
    throws IllegalDirectionException
  {
    saveCurrentDirection();
     moveInDirection(current_direction_);
  }

//-----------------------------------------------------------------------------
/**
 * method to move a robot one cell back (relative to his current direction)
 * @exception IllegalDirectionException thrown if the direction isn't valid.
 */
  public void goBack()
    throws IllegalDirectionException
  {
  saveCurrentDirection();
    current_direction_.invertDirection();
    moveInDirection(current_direction_);
  }

//-----------------------------------------------------------------------------
/**
 * method to move a robot one cell left (relative to his current direction)
 * @exception IllegalDirectionException thrown if the direction isn't valid.
 */
  public void goLeft()
    throws IllegalDirectionException
  {
  saveCurrentDirection();
    switch(current_direction_.getDirection())
    {
      case Direction.DIRECTION_UP:
        current_direction_.setDirection(Direction.DIRECTION_LEFT);
        break;
      case Direction.DIRECTION_DOWN:
        current_direction_.setDirection(Direction.DIRECTION_RIGHT);
        break;
      case Direction.DIRECTION_LEFT:
        current_direction_.setDirection(Direction.DIRECTION_DOWN);
        break;
      case Direction.DIRECTION_RIGHT:
        current_direction_.setDirection(Direction.DIRECTION_UP);
        break;
      default:
        break;
    }
    moveInDirection(current_direction_);
  }

//-----------------------------------------------------------------------------
/**
 * method to move a robot one cell right (relative to his current direction)
 * @exception IllegalDirectionException thrown if the direction isn't valid.
 */
  public void goRight()
    throws IllegalDirectionException
  {
  saveCurrentDirection();
    switch(current_direction_.getDirection())
    {
      case Direction.DIRECTION_UP:
        current_direction_.setDirection(Direction.DIRECTION_RIGHT);
        break;
      case Direction.DIRECTION_DOWN:
        current_direction_.setDirection(Direction.DIRECTION_LEFT);
        break;
      case Direction.DIRECTION_LEFT:
        current_direction_.setDirection(Direction.DIRECTION_UP);
        break;
      case Direction.DIRECTION_RIGHT:
        current_direction_.setDirection(Direction.DIRECTION_DOWN);
        break;
      default:
        break;
    }
    moveInDirection(current_direction_);
  }

//-----------------------------------------------------------------------------
/**
 * method to get the count of things the robot is carrying
 * @return count of taken things
 */
  public int getNumTakenThings()
  {
    return num_taken_things_;
  }

//-----------------------------------------------------------------------------
/**
 * method to get pick up a new thing
 *
 */
  public void pickUpThing()
  {
	 num_taken_things_++;
  }

//-----------------------------------------------------------------------------
/**
 * method to get remove all things from the Robot
 *
 */
  public void putDownAllThing()
  {
   num_taken_things_=0;
  }

//-----------------------------------------------------------------------------
/**
 * method to get the count of steps from the Robot
 * @return count of steps the robot made
 */
  public int getNumSteps()
  {
   return step_count_;
  }
}
