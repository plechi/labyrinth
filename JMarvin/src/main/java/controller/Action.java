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
 * class representing an action
 */

public class Action {
    public final static int MOVE_AHEAD = 1;
    public final static int MOVE_RIGHT = 2;
    public final static int MOVE_BACK = 3;
    public final static int MOVE_LEFT = 4;

    protected int cell_mark_;
    protected int state_;
    protected int moving_direction_;

    // -----------------------------------------------------------------------------
    /**
     * Standard constructor
     * 
     * @param state
     *            internal state, value have to be greater than -1
     * @param cell_mark
     *            value have to be greater than -1
     * @param moving_direction
     *            relative direction (value between 1 and 4)
     * @exception IllegalStateException
     *                thrown when trying to set a wrong value of state,
     *                cell_mark or moving_direction
     */
    public Action(int state, int cell_mark, int moving_direction)
            throws IllegalArgumentException {
        if (state < -1)
            throw (new IllegalArgumentException("state must be >= -1"));

        if (moving_direction < 1 || moving_direction > 4)
            throw (new IllegalArgumentException(
                    "invalid moving_direction. possible directions MOVE_AHEAD, "
                            + "MOVE_BACK, MOVE_LEFT, MOVE_RIGHT "));

        if (cell_mark < -1)
            throw (new IllegalArgumentException("cell_mark must be >= -1"));

        moving_direction_ = moving_direction;
        cell_mark_ = cell_mark;
        state_ = state;
    }

    public Action() {
        moving_direction_ = 1;
        cell_mark_ = -1;
        state_ = -1;
    }

    // ----------------------------------------------------------------------------

    /**
     * copy constructor
     * 
     * @param action
     *            Action to be copyed
     */
    public Action(Action action) {
        moving_direction_ = action.getMovingDirection();
        cell_mark_ = action.getCellMark();
        state_ = action.GetState();
    }

    // -----------------------------------------------------------------------------
    /**
     * method equals with an action
     * 
     * @param action
     * @return true, if it is, otherwise false
     */
    public boolean equals(Action action) {
        if (moving_direction_ == action.getMovingDirection()
                && cell_mark_ == action.getCellMark()
                && state_ == action.GetState())
            return true;
        else
            return false;
    }

    // -----------------------------------------------------------------------------

    /**
     * method sets the value of state
     *
     * @param state
     * @exception IllegalStateException
     *                thrown when trying to set a wrong value of state
     */
    public void setState(int state) throws IllegalArgumentException {
        if (state < -1)
            throw (new IllegalArgumentException("state must be >= -1"));
        state_ = state;
    }

    // -----------------------------------------------------------------------------

    /**
     * method returns a relative direction.
     *
     * @return the value of moving direction
     */
    public int getMovingDirection() {
        return moving_direction_;
    }

    // -----------------------------------------------------------------------------

    /**
     * method sets a relative direction used to move the robot.
     * 
     * @param moving_direction
     *            possible directions are ahead, back, left and rigth.
     */
    public void setMovingDirection(int moving_direction)
            throws IllegalArgumentException {
        if (moving_direction < 1 || moving_direction > 4)
            throw (new IllegalArgumentException(
                    "invalid moving_direction. possible directions MOVE_AHEAD, "
                            + "MOVE_BACK, MOVE_LEFT, MOVE_RIGHT "));

        moving_direction_ = moving_direction;
    }

    // -----------------------------------------------------------------------------

    /**
     * method returns the value of the cell mark
     *
     * @return the value of cell mark
     */
    public int getCellMark() {
        return cell_mark_;
    }

    // -----------------------------------------------------------------------------

    /**
     * method sets the value of cell mark
     *
     * @param cell_mark 0 if the cell is not marked 1 if the cell is marked for the
     *                  first time 2 if the cell was marked before
     * @throws IllegalStateException thrown when trying to set a wrong value of cell_mark
     */
    public void setCellMark(int cell_mark) throws IllegalArgumentException {
        if (cell_mark < -1)
            throw (new IllegalArgumentException("cell_mark must be >= -1"));

        cell_mark_ = cell_mark;
    }

    // -----------------------------------------------------------------------------

    /**
     * method returns the value of the state
     * 
     * @return the value of state
     */
    public int GetState() {
        return state_;
    }

    // -----------------------------------------------------------------------------
    /**
     * toString method for debugging
     * 
     * @return info about the action in string-format
     */
    public String toString() {
        String direction = "";

        switch (moving_direction_) {
        case (MOVE_AHEAD):
            direction = "nach vorn";
            break;
        case (MOVE_BACK):
            direction = "umdrehen";
            break;
        case (MOVE_LEFT):
            direction = "nach links";
            break;
        case (MOVE_RIGHT):
            direction = "nach rechts";
            break;
        }
        return (cell_mark_ + "," + direction + "," + state_);
    }

}
