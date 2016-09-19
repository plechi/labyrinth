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
 * class representing a Condition
 */
public class Condition {
    protected boolean wall_left_;
    protected boolean wall_right_;
    protected boolean wall_ahead_;
    protected int cell_mark_;
    protected int state_;

    /**
     * Standard constructor
     * 
     * @param state
     *            internal state, value have to be greater than -1
     * @param cell_mark
     *            value have to be greater than -1
     * @param wall_left
     *            is true, if there is a wall
     * @param wall_right
     *            is true, if there is a wall
     * @param wall_ahead
     *            is true, if there is a wall
     * @exception IllegalStateException
     *                thrown when trying to set a wrong value of state,
     *                cell_mark or moving_direction
     */
    public Condition(int state, int cell_mark, boolean wall_left,
            boolean wall_right, boolean wall_ahead)
            throws IllegalArgumentException {
        if (cell_mark < -1)
            throw (new IllegalArgumentException("cell_mark must be >= -1"));
        if (state < -1)
            throw (new IllegalArgumentException("state must be >= -1"));

        wall_left_ = wall_left;
        wall_right_ = wall_right;
        wall_ahead_ = wall_ahead;
        cell_mark_ = cell_mark;
        state_ = state;
    }

    public Condition() {
        wall_left_ = false;
        wall_right_ = false;
        wall_ahead_ = false;
        cell_mark_ = -1;
        state_ = -1;
    }

    /**
     * copy constructor
     * 
     * @param condition
     *            Condition to be copyed
     */
    public Condition(Condition condition) {
        wall_left_ = condition.isWallLeft();
        wall_right_ = condition.isWallRight();
        wall_ahead_ = condition.isWallAhead();
        cell_mark_ = condition.getCellMark();
        state_ = condition.getState();
    }

    // -----------------------------------------------------------------------------
    /**
     * method sets if the cells in field of vision of the robot are ways or
     * walls
     * 
     * @param wall_left
     *            false means that the cell on the left of the robot is a way
     * @param wall_right
     *            false means that the cell on the right of the robot is a way
     * @param wall_ahead
     *            false means that the cell in front of the robot is a way
     */
    public void setWalls(boolean wall_left, boolean wall_right,
            boolean wall_ahead) {
        wall_left_ = wall_left;
        wall_right_ = wall_right;
        wall_ahead_ = wall_ahead;
    }

    // -----------------------------------------------------------------------------

    /**
     * find out whether there is a wall at the left side
     *
     * @return true if there is a wall left, false otherwise
     */
    public boolean isWallLeft() {
        return wall_left_;
    }

    // -----------------------------------------------------------------------------

    /**
     * find out whether there is a wall at the right side
     *
     * @return true if there is a wall right, false otherwise
     */
    public boolean isWallRight() {
        return wall_right_;
    }

    // -----------------------------------------------------------------------------

    /**
     * find out whether there is a wall ahead
     *
     * @return true if there is a wall ahead, false otherwise
     */
    public boolean isWallAhead() {
        return wall_ahead_;
    }

    // -----------------------------------------------------------------------------

    /**
     * find out the cell mark
     *
     * @return true the cell mark
     */
    public int getCellMark() {
        return cell_mark_;
    }

    // -----------------------------------------------------------------------------

    /**
     * method sets the cell mark
     *
     * @param cell_mark
     *            0 if the cell is not marked 1 if the cell is marked for the
     *            first time 2 if the cell was marked before
     * @exception IllegalStateException
     *                thrown when trying to set a wrong value of cell_mark
     */
    public void setCellMark(int cell_mark) throws IllegalArgumentException {
        if (cell_mark < -1)
            throw (new IllegalArgumentException("cell_mark must be >= -1"));

        cell_mark_ = cell_mark;
    }

    // -----------------------------------------------------------------------------

    /**
     * find out the state
     *
     * @return true the state
     */
    public int getState() {
        return state_;
    }

    // -----------------------------------------------------------------------------

    /**
     * method sets the state
     *
     * @param state
     * @throws IllegalStateException thrown when trying to set a wrong value of state
     */
    public void setState(int state) throws IllegalArgumentException {
        if (state < -1)
            throw (new IllegalArgumentException("state must be >= -1"));
        state_ = state;
    }

    // -----------------------------------------------------------------------------

    /**
     * method compares 2 conditions and returns true if they are equal.
     * 
     * @return true if they are equal
     */
    public boolean equals(Condition test_condition) {
        return ((wall_left_ == test_condition.isWallLeft())
                && (wall_right_ == test_condition.isWallRight())
                && (wall_ahead_ == test_condition.isWallAhead())
                && (cell_mark_ == test_condition.getCellMark()) && (state_ == test_condition
                    .getState()));
    }

    // -----------------------------------------------------------------------------
    /**
     * toString method for debugging
     * 
     * @return info about the condition in string-format
     */
    public String toString() {
        String walls = "";

        if (wall_left_)
            walls += "F";
        else
            walls += "G";

        if (wall_ahead_)
            walls += "F";
        else
            walls += "G";

        if (wall_right_)
            walls += "F";
        else
            walls += "G";

        return (walls + "," + state_ + "," + cell_mark_);

    }

}
