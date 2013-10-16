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

//------------------------------------------------------------
/**
 *  A cell that is used in the labyrinth
 */

public class Cell
{
      /** status flags */
  protected final static int WALL =               0x00000001;
  protected final static int WAY =                0x00000002;
  protected final static int STARTPOINT =         0x00000004;
  protected final static int CONTAINS_DIAMOND =   0x00000008;
  protected final static int EMPTY =              0x00000010;
  protected final static int VISITED =            0x00000020;
      /** member variable to store the flags in */
  protected int status_;
  protected int cell_mark_;

//------------------------------------------------------------
/**
 * sets the cell mark
 * @param cell_mark
 */
  public void setCellMark(int cell_mark)
  {
    cell_mark_ = cell_mark;
  }
  
//------------------------------------------------------------
/**
 * find out the cell mark
 * @return the value of the cell mark
 */  
  public int getCellMark()
  {
    return cell_mark_;
  }
  
//------------------------------------------------------------
/**
 * find out whether the cell is a wall
 * @return true if the cell is a wall, false otherwise
 */
  public Cell()
  {
    status_ = WALL;
    cell_mark_ = -1;
  }

//------------------------------------------------------------
/**
 * find out whether the cell is a wall
 * @return true if the cell is a wall, false otherwise
 */
  public boolean isWall()
  {
    return((status_ & WALL) != 0);
  }

//------------------------------------------------------------
/**
 * covert a way or a empty cell to wall
 * @exception IllegalStateException thrown if the cell is allready
 * a wall, the cell contains a diamond or a robot.
 */
  public void convertToWall()
    throws IllegalStateException
  {
    if ((status_ & WALL) != 0)
      throw(new IllegalStateException(
              "can't convert to a wall, cell is already a wall"));
    if ((status_ & CONTAINS_DIAMOND) != 0)
        throw(new IllegalStateException(
              "can't convert to a wall, cell contains a diamond"));
    if ((status_ & WAY) != 0)
      status_ &= ~WAY;
    status_ |= WALL;
  }

//------------------------------------------------------------
/**
 * find out whether the cell is a way
 * @return true if the cell is a way, false otherwise
 */
  public boolean isWay()
  {
    return((status_ & WAY) != 0);
  }

//------------------------------------------------------------
/**
 * covert a cell to a way
 * @exception IllegalStateException thrown if the cell is allready
 * a way.
 */

  public void convertToWay()
    throws IllegalStateException
  {
    if ((status_ & WAY) != 0)
      throw(new IllegalStateException(
              "can't convert to a way, cell is already a way"));
    if ((status_ & WALL) != 0)
      status_ &= ~WALL;
    status_ |= WAY;
  }
//------------------------------------------------------------
/**
 * find out whether the cell is a startpoint
 * @return true if the cell is a startpoint, false otherwise
 */
  public boolean isStartpoint()
  {
    return((status_ & STARTPOINT) != 0);
  }

//------------------------------------------------------------
/**
 * covert a cell to a startpoint
 * @exception IllegalStateException thrown if the cell is allready
 * a startpoint.
 */
  public void convertToStartpoint()
    throws IllegalStateException
  {
    if ((status_ & STARTPOINT) != 0)
      throw(new IllegalStateException(
              "can't convert to a startpoint, cell is already a startpoint"));
    if ((status_ & WALL) != 0)
  {
    status_ &= ~WALL;
    status_ |= WAY;
  }
  status_ |= STARTPOINT;
  }

//------------------------------------------------------------
/**
 * remove a startpoint
 * @exception IllegalStateException thrown when trying to remove a
 * startpoint form a cell that doesn't contain a startpoint
 */
  public void removeStartpoint()
    throws IllegalStateException
  {
    if ((status_ & STARTPOINT) == 0)
      throw(new IllegalStateException(
              "can't remove a startpoint, cell doesn't contain a startpoint"));
    status_ &= ~STARTPOINT;
  }

//------------------------------------------------------------
/**
 * find out whether the cell contains a diamand
 * @return true if the cell contains a diamand, false otherwise
 */

  public boolean containsDiamond()
  {
    return((status_ & CONTAINS_DIAMOND) != 0);
  }

//------------------------------------------------------------
/**
 * set a diamand
 * @exception IllegalStateException thrown when trying to place a
 * diamond in a cell that already contains a diamond
 */
  public void setDiamond()
    throws IllegalStateException
  {
    if ((status_ & CONTAINS_DIAMOND)!=0)
      throw(new IllegalStateException(
              "can't set diamond, cell contains already a diamond"));
    status_ |= CONTAINS_DIAMOND;
  }

//------------------------------------------------------------
/**
 * find out whether the cell is empty
 * @return true if the cell is empty, false otherwise
 */
 public boolean isEmpty()
  {
    return((status_ & EMPTY) != 0);
  } 
  
//------------------------------------------------------------
/**
 * remove a diamond
 * @exception IllegalStateException thrown when trying to remove a
 * diamond form a cell that doesn't contain a diamond
 */
  public void removeDiamond()
    throws IllegalStateException
  {
    if ((status_ & CONTAINS_DIAMOND) == 0)
      throw(new IllegalStateException(
              "can't remove a diamond, cell doesn't contain a diamond"));
    status_ &= ~CONTAINS_DIAMOND;
  }

//------------------------------------------------------------
 /**
 * remove the visited state
 * @exception IllegalStateException thrown when trying to remove the
 * visited state form a cell that isn't markt as visited
 */
  public void removeVisitedState()
    throws IllegalStateException
  {
    if ((status_ & VISITED) == 0)
      throw(new IllegalStateException(
              "can't mark cell as unvisited, cell is already unvisited"));
    status_ &= ~VISITED;
  }

//------------------------------------------------------------
/**
 * set the visited state
 * @exception IllegalStateException thrown when trying to set
 * the visited state in a cell that is already visited
 */
  public void setVisitedState()
    throws IllegalStateException
  {
    if ((status_ & VISITED)!=0)
      throw(new IllegalStateException(
              "can't mark cell as visited, cell is already visited"));
    status_ |= VISITED;
  } 
  
//------------------------------------------------------------
/**
 * find out whether the visited state was set
 * @return true if the celk was visited or not
 */
  public boolean isVisited()
  {
    return((status_ & VISITED) != 0);
  } 
  
//------------------------------------------------------------

/**
 * resets the cell including status
 */
  public void resetAll()
  {
    status_ = 0;
  }

//------------------------------------------------------------
/**
 * marks the cell as empty
 * @exception IllegalStateException thrown when trying to set
 * the empty state in a cell that is already empty
 */
public void setEmpty()
  throws IllegalStateException
{
  if (status_  == EMPTY)
      throw(new IllegalStateException(
              "can't set an empty cell"));
    status_ = EMPTY;
}

//------------------------------------------------------------
/**
 * standard toString method for debugging
 * @return info about the cell in string-format
 */
  public String toString()
  {
    return("status_: " + status_);
  }

}
