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
/**
 * class generates a random coordiate in the labyrinth field
 */
public class RandomWayCoordinates
{

private int rows_, cols_;
private int random_row_, random_col_;
private Random random_generator_;

//-----------------------------------------------------------------------------
/**
 * Standard constructor
 * @param rows rowcount of the labyrinth field
 * @param cols colcount of the labyrinth field
 * @exception IllegalStateException thrown when trying to set a wrong value
 * of rows and cols
 */
  public RandomWayCoordinates(int rows,int cols)
      throws IllegalArgumentException
    {
      if (rows <= 1)
        throw(new IllegalArgumentException(
          "rows must be >= 1"));
      if (cols <= 1)
        throw(new IllegalArgumentException(
        "rows must be >= 1"));
      random_generator_ = new Random();
      rows_=rows;
      cols_=cols;
      random_row_=-1;
      random_col_=-1;
  }
  
//-----------------------------------------------------------------------------
/**
 * Standard constructor
 * @param rows rowcount of the labyrinth field
 * @param cols colcount of the labyrinth field
 * @exception IllegalStateException thrown when trying to set a wrong value
 * of rows and cols
 */
  public boolean findRandomWayCoordinates(LabyrinthField labyrinth_field)
  {
        random_row_=-1;
        random_col_=-1;
    int min=1;
    int max=labyrinth_field.getNumWayCells();
    int cell_number = getIntRandomValue(min, max);
    int rows_count, cols_count;
    int way_cell_counter=0;
    for(rows_count=0;rows_count<rows_;rows_count++)
    {
      for(cols_count=0;cols_count<cols_;cols_count++)
      {
        if((labyrinth_field.isWay(rows_count, cols_count)==true)&&(labyrinth_field.isStartpoint(rows_count, cols_count)==false))
          way_cell_counter++;
        if(way_cell_counter==cell_number)
        {
          random_row_=rows_count;
          random_col_=cols_count;
          return true;
        }
      }
    }
    return false;
  }
  
//-----------------------------------------------------------------------------
/**
 * Askes for random way coordinate
 *@return random_row
 * @exception IllegalStateException thrown when rows is not set
 */
  public int getRandomWayRowCoordinate()
    throws IllegalArgumentException
  {
    if (random_row_ < 1)
      throw(new IllegalArgumentException(
            "random_row_ must be > 1"));
    return random_row_;
  }

//-----------------------------------------------------------------------------
/**
 * Askes for random way coordinate
 *@return random_col
 * @exception IllegalStateException thrown when col is not set
 */  
  public int getRandomWayColCoordinate()
    throws IllegalArgumentException
  {
    if (random_col_ < 1)
      throw(new IllegalArgumentException(
            "random_col_ must be > 1"));
    return random_col_;
  }

//-----------------------------------------------------------------------------
/**
 * Askes for random way length between min and max
 *@return random_length
 */ 
  protected int getIntRandomValue(int min, int max)
  {
      return min + random_generator_.nextInt(max-min+1);
    }
}