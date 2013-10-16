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
 * Implementation of a two dimensional field
 */

public class Field
{
  protected Object[][] field_;  
//------------------------------------------------------------
/**
 * standard constructor
 * @param rows the number of rows of the field
 * @param cols the number of columns of the field
 * @exception IllegalArgumentException thrown if either rows or cols
 * is < 0
 */
  public Field(int rows, int cols)
    throws IllegalArgumentException
  {
    if ((rows < 0) || (cols < 0))
      throw(new IllegalArgumentException("rows or cols < 0"));
        // don't allocate an array with either rows or cols being 0
    if ((rows > 0) && (cols > 0))
      field_ = new Object[rows][cols];
  }
  
//------------------------------------------------------------
/**
 * returns an element as defined by the given indices
 * @param row the row of the desired element
 * @param col the column of the desired element
 * @exception IndexOutOfBoundsException thrown if the index is outside
 * of the allocated range
 */
  private Object getElement(int row,int col)
    throws IndexOutOfBoundsException
  {
    if (field_ == null)
      throw(new IndexOutOfBoundsException("field has zero size"));
    return(field_[row][col]);
  }

//------------------------------------------------------------
/**
 * returns the number of allocated rows
 * @return the number of allocated rows
 */
  public int getNumRows()
  {
    if (field_ == null)
      return(0);
    return(field_.length);
  }

//------------------------------------------------------------
/**
 * returns the number of allocated columns
 * @return the number of allocated columns
 */
  public int getNumCols()
  {
    if (field_ == null)
      return(0);
    return(field_[0].length);
  }

//------------------------------------------------------------
/**
 * standard toString method for debugging
 * @return info about the field in string-format
 */
  public String toString()
  {
    if (field_ == null)
      return("field with zero size");
    return("rows: " + field_.length + ", cols: " +
           field_[0].length);
  }

}
