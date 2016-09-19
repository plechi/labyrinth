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
 * class representing a two dimensional labyrinth
 */
public class LabyrinthField extends Field implements Cloneable {
    /**
     * the number of diamonds distributed in the field (set from outside of the
     * class with the appropriate method-calls
     */
    protected int num_diamonds_;
    /**
     * the number of way cells distributed in the field (set from outside of the
     * class with the appropriate method-calls
     */
    protected int num_way_cells_;
    /**
     * the number of none way cells e.g.way cells (set from outside of the class
     * with the appropriate method-calls
     */
    protected int num_wall_cells_;

    protected int start_point_row_;
    protected int start_point_col_;
    protected Direction start_direction_;
    protected String name_;
    protected int rws, cls;

    // -----------------------------------------------------------------------------
    /**
     * Standard constructor
     * 
     * @param rows
     *            the number of rows of this labyrinth
     * @param cols
     *            the number of columns of this labyrinth
     * @exception IllegalArgumentException
     *                thrown if either rows or cols is < 0
     */
    public LabyrinthField(int rows, int cols) throws IllegalArgumentException {
        super(rows, cols);
        rws = rows;
        cls = cols;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                field_[row][col] = new Cell();
                Cell cell = (Cell) field_[row][col];
                // cell.convertToWall();
            }
        }
        num_wall_cells_ = rows * cols;
        num_way_cells_ = 0;
        num_diamonds_ = 0;
        start_point_row_ = -1;
        start_point_col_ = -1;
        start_direction_ = null;
        name_ = new String();
    }

    // -----------------------------------------------------------------------------
    /**
     * This is used to make a copy of the whole object, but unlike what one
     * would expect from a clone() method, this one does a deep copy! This
     * function is intended to be called from loadLabyrinth(), where backups of
     * the labyrinth are necessary.
     * 
     * @see LevelHandler#loadLevel
     * @author Bernhard Kornberger
     */
    public Object clone() {
        try {
            LabyrinthField ret = (LabyrinthField) super.clone();
            ret.start_direction_ = new Direction(this.start_direction_);
            ret.field_ = new Object[rws][cls];
            for (int row = 0; row < rws; row++)
                for (int col = 0; col < cls; col++) {
                    Cell cell = new Cell();
                    cell.status_ = ((Cell) this.field_[row][col]).status_;
                    cell.cell_mark_ = ((Cell) this.field_[row][col]).cell_mark_;
                    ret.field_[row][col] = (Object) cell;
                }
            return (Object) ret;
        } catch (CloneNotSupportedException e) {
            System.out.println("LabyrinthField.clone() failed!");
            return new Object();
        } // Attention, in case this exeption is thrown we return just a new
          // object!
    }

    // -----------------------------------------------------------------------------
    /**
     * Sets one number to the cell.
     * 
     * @param row
     *            Set the cellmark to this row
     * @param col
     *            Set the cellmark to this column
     * @param cell_mark
     *            is the number to be written
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value of state
     */
    public void setCellMark(int row, int col, int cell_mark)
            throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        if (cell_mark < -1)
            throw (new IllegalArgumentException("cell_mark is not valid"));

        Cell cell = (Cell) field_[row][col];
        cell.setCellMark(cell_mark);
    }

    // -----------------------------------------------------------------------------
    /**
     * Sets a diamond at a definite position
     * 
     * @param row
     *            row position
     * @param col
     *            col position
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void setDiamond(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));

        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        Cell cell = (Cell) field_[row][col];
        if ((cell.containsDiamond() == false) && (cell.isWay() == true)
                && (cell.isStartpoint() == false))
            ;
        {
            cell.setDiamond();
            num_diamonds_++;
        }
    }

    // -----------------------------------------------------------------------------
    /**
     * Removes a diamond at a definite position
     * 
     * @param row
     *            row position
     * @param col
     *            col position
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void removeDiamond(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        Cell cell = (Cell) field_[row][col];
        if ((cell.containsDiamond() == true) && (cell.isWay() == true)
                && (cell.isStartpoint() == false))
            ;
        {
            cell.removeDiamond();
            num_diamonds_--;

        }
    }

    // -----------------------------------------------------------------------------

    /**
     * Gets the name of the labyrinthfield
     *
     * @return name
     */
    public String getName() {
        return name_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Sets the name of the labyrinthfield
     *
     * @param name
     */
    public void setName(String name) {
        name_ = name;
    }

    // -----------------------------------------------------------------------------

    /**
     * Maks a definite position as a startpoint for a robot
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void convertToStartpoint(int row, int col)
            throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        if ((start_point_row_ == row) && (start_point_col_ == col))
            throw (new IllegalArgumentException(
                    "can not convert to a startpoint, cell is a startpoint"));

        Cell cell = (Cell) field_[row][col];
        if (cell.isWay() == true) {
            num_wall_cells_--;
            num_way_cells_++;
        } else
            num_way_cells_++;
        start_point_row_ = row;
        start_point_col_ = col;
        cell.convertToWay();
    }

    // -----------------------------------------------------------------------------

    /**
     * Removes the start point for the robot at a definite position
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void removeStartpoint(int row, int col)
            throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        if ((start_point_row_ != row) || (start_point_col_ != col))
            throw (new IllegalArgumentException(
                    "can not remove startpoint, cell is not a startpoint"));

        if ((start_point_row_ == row) && (start_point_col_ == col)) {
            start_point_row_ = -1;
            start_point_col_ = -1;
        }
    }

    // -----------------------------------------------------------------------------

    /**
     * Maks a definite position as way cell
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void convertToWay(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        Cell cell = (Cell) field_[row][col];
        if (cell.isWay() == true)
            throw (new IllegalArgumentException(
                    "can not convert a Waycell to a Way"));

        num_way_cells_++;
        num_wall_cells_--;
        cell.convertToWay();
    }

    // -----------------------------------------------------------------------------

    /**
     * Maks a definite position as way cell
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void convertToWall(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        if ((start_point_row_ == row) && (start_point_col_ == col))
            throw (new IllegalArgumentException(
                    "can not convert to wall, cel is a startpoint"));

        Cell cell = (Cell) field_[row][col];
        if (cell.isWall() == true)
            throw (new IllegalArgumentException(
                    "can not convert a Wallcell to a Wall"));

        num_way_cells_--;
        num_wall_cells_++;
        cell.convertToWall();
    }

    // -----------------------------------------------------------------------------

    /**
     * Maks a definite position as visited i.e something was at this position
     * before
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void setVisited(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        Cell cell = (Cell) field_[row][col];
        if (cell.isVisited() == false)
            cell.setVisitedState();
    }

    // -----------------------------------------------------------------------------

    /**
     * Unmaks a definite position as visited
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     */
    public void setUnVisited(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        Cell cell = (Cell) field_[row][col];
        if (cell.isVisited() == true)
            cell.removeVisitedState();
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out whether the cell at the postion is marked as visited or not
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return true if it is marked as visited, otherwise false
     */
    public boolean isVisited(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row > getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col > getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        Cell cell = (Cell) field_[row][col];
        return cell.isVisited();
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out how many diamonds were set
     *
     * @return number of diamonds
     */
    public int getNumDiamonds() {
        return num_diamonds_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out how many way cells exists
     *
     * @return number of waycells
     */
    public int getNumWayCells() {
        return num_way_cells_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out how many wall cells exists
     *
     * @return number of wallcells
     */
    public int getNumWallCells() {
        return num_wall_cells_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out if a definite position is marked as way cell
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return true if it is marked as waycell, otherwise false
     */
    public boolean isWay(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        Cell cell = (Cell) field_[row][col];
        return cell.isWay();
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out if a definite position is marked as wall cell
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return true if it is marked as waycell, otherwise false
     */
    public boolean isWall(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException("row (" + row
                    + ") must be >= 0 and < NumRows (" + getNumRows() + ")"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException("col (" + col
                    + ") must be >= 0 and < NumCols (" + getNumCols() + ")"));

        Cell cell = (Cell) field_[row][col];
        return cell.isWall();
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out if a definite position is marked as startpoint
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return true if it is marked as startpoint, otherwise false
     */
    public boolean isStartpoint(int row, int col)
            throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        if ((start_point_row_ == row) && (start_point_col_ == col))
            return true;
        else
            return false;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out if there is a cell marked as startpoint in the field
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return true if a startpoint is marked, otherwise false
     */
    public boolean isStartpoint() {
        if ((start_point_row_ == -1) || (start_point_col_ == -1))
            return false;
        else
            return true;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out the cell mark at a definite position
     *
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return cell mark
     */
    public int getCellMark(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        Cell cell = (Cell) field_[row][col];
        // System.out.println("Cell: "
        // +cell.nameOfTheCell+"CELLMARK="+cell.getCellMark());
        return cell.getCellMark();
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out the startpoint row
     *
     * @exception IllegalArgumentException
     *                thrown when startpoint is not set
     * @return start point row
     */
    public int getStartpointRow() throws IllegalArgumentException {
        if (start_point_row_ == -1)
            throw (new IllegalArgumentException("startpoint is not set"));
        return start_point_row_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out the startpoint col
     *
     * @exception IllegalArgumentException
     *                thrown when startpoint is not set
     * @return start point row
     */
    public int getStartpointCol() throws IllegalArgumentException {
        if (start_point_col_ == -1)
            throw (new IllegalArgumentException("startpoint is not set"));

        return start_point_col_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out the start direction for the robot
     *
     * @return start direction
     */
    public Direction getStartDirection() {
        return start_direction_;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out if the start direction was set
     *
     * @return start direction
     */
    public boolean isStartDirection() {
        if (start_direction_ != null)
            return true;
        else
            return false;
    }

    // -----------------------------------------------------------------------------

    /**
     * Sets the start direction for the robot
     *
     * @param direction
     */
    public void setStartDirection(Direction direction) {
        start_direction_ = direction;
    }

    // -----------------------------------------------------------------------------

    /**
     * Find out if the start direction was set
     * 
     * @return start direction
     */
    public boolean containsDiamond(int row, int col)
            throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        Cell cell = (Cell) field_[row][col];
        return cell.containsDiamond();
    }

    // -----------------------------------------------------------------------------
    /**
     * package-internal method that returns if all diamonds were found according
     * to the internal counters
     * 
     * @return true, if all diamonds were found, otherwise false
     */
    public boolean wereAllDiamondsFound() {
        return (num_diamonds_ == 0);
    }

    // -----------------------------------------------------------------------------
    /**
     * package-internal method that returns if all diamonds were found according
     * to the internal counters
     * 
     * @return true, if all diamonds were found, otherwise false
     */
    public void setEmpty(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));

        Cell cell = (Cell) field_[row][col];
        if (cell.isWay() == true) {
            num_way_cells_--;
        } else if (cell.isWall()) {
            num_wall_cells_--;
        } else if ((start_point_row_ == row) || (start_point_col_ == col)) {
            start_point_row_ = -1;
            start_point_col_ = -1;
        }

        cell.setEmpty();
    }

    // -----------------------------------------------------------------------------
    /**
     * Find out, if a definite position is empty
     * 
     * @param row
     * @param col
     * @exception IllegalArgumentException
     *                thrown when trying to set a wrong value for row or col
     * @return true, if it is empty
     */
    public boolean isEmpty(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= getNumRows())
            throw (new IllegalArgumentException(
                    "row must be >= 0 and < NumRows"));
        if (col < 0 || col >= getNumCols())
            throw (new IllegalArgumentException(
                    "col must be >= 0 and < NumCols"));
        if ((start_point_row_ != row) || (start_point_col_ != col)) {
            Cell cell = (Cell) field_[row][col];
            return cell.isEmpty();
        } else
            return false;
    }

    // ----------------------------------------------------------------------------
    /**
     * standard toString method for debugging
     * 
     * @return info about the labyrinth field in string-format
     */
    public String toString() {
        return ("Labyrinthfield: num_diamonds_ = " + num_diamonds_
                + ", num_way_cells_ = " + num_way_cells_
                + ", num_wall_cells_ = " + num_wall_cells_ + super.toString());
    }

}
