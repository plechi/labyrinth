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
 * class representing a direction and provides constants for using and writing
 * directions
 */
public class Direction {
    public final static int DIRECTION_UP = 1;
    public final static int DIRECTION_RIGHT = 2;
    public final static int DIRECTION_DOWN = 3;
    public final static int DIRECTION_LEFT = 4;

    public final static String DIR_UP = "UP";
    public final static String DIR_RIGHT = "RIGHT";
    public final static String DIR_DOWN = "DOWN";
    public final static String DIR_LEFT = "LEFT";

    /** current direction */
    protected int direction_;
    /** static random generator for generating random directions */
    protected static Random random_generator_;

    // ------------------------------------------------------------------------------
    /**
     * constructor sets the direction to a non legal value and creates a new
     * random generator
     */
    public Direction() {
        direction_ = 0;
        random_generator_ = new Random();
    }

    public Direction(Direction dir) {
        this.direction_ = dir.direction_;
    }

    public boolean equals(Direction direction) {
        if (this.direction_ == direction.direction_)
            return true;
        else
            return false;
    }

    // ------------------------------------------------------------------------------
    /**
     * method detects whether the direction is legal or not
     * 
     * @param direction
     *            direction constant to check
     * @return true if the direction is legal
     */
    protected boolean isLegalDirection(int direction) {
        if (direction == DIRECTION_UP || direction == DIRECTION_RIGHT
                || direction == DIRECTION_DOWN || direction == DIRECTION_LEFT)
            return true;
        else
            return false;
    }

    // ------------------------------------------------------------------------------
    /**
     * method returns the current direction
     * 
     * @return direction constant
     * @exception IllegalDirectionException
     *                thrown if the current direction is not legal
     */
    public int getDirection() throws IllegalDirectionException {
        if (!isLegalDirection(direction_)) {
            throw new IllegalDirectionException("Illegal Direction!");
        }

        return direction_;
    }

    // ------------------------------------------------------------------------------
    /**
     * method to set the current direction
     * 
     * @param direction
     *            new direction constant
     * @exception IllegalDirectionException
     *                thrown if the new direction constant is not legal
     */
    public void setDirection(int direction) throws IllegalDirectionException {
        if (isLegalDirection(direction)) {

            direction_ = direction;
        } else {
            throw new IllegalDirectionException("Illegal Direction!");
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * method turns the direction around 180°
     * 
     * @exception IllegalDirectionException
     *                thrown if the current direction is not legal
     */
    public void invertDirection() throws IllegalDirectionException {
        if (!isLegalDirection(direction_)) {
            throw new IllegalDirectionException("Illegal Direction!");
        }

        switch (direction_) {
        case DIRECTION_UP:
            direction_ = DIRECTION_DOWN;
            break;
        case DIRECTION_DOWN:
            direction_ = DIRECTION_UP;
            break;
        case DIRECTION_RIGHT:
            direction_ = DIRECTION_LEFT;
            break;
        case DIRECTION_LEFT:
            direction_ = DIRECTION_RIGHT;
            break;
        default:
            break;
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * method generates a new direction but not the invertet direction
     * 
     * @exception IllegalDirectionException
     *                thrown if the current direction is not legal
     */
    public void randomizeNextDirection() throws IllegalDirectionException {
        invertDirection();
        int forbidden_direction = direction_;

        while (direction_ == forbidden_direction) {
            randomizeDirection();
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * method generates a new direction - every direction is possible
     */
    protected void randomizeDirection() {
        int rand_value = random_generator_.nextInt(4) + 1;
        switch (rand_value) {
        case 1:
            direction_ = DIRECTION_UP;
            break;
        case 2:
            direction_ = DIRECTION_RIGHT;
            break;
        case 3:
            direction_ = DIRECTION_DOWN;
            break;
        case 4:
            direction_ = DIRECTION_LEFT;
            break;
        default:
            break;
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * standard toString method for debugging
     * 
     * @return info about the direction in string-format
     */
    public String toString() {
        String ret_string;
        switch (direction_) {
        case DIRECTION_UP:
            ret_string = DIR_UP;
            break;
        case DIRECTION_DOWN:
            ret_string = DIR_DOWN;
            break;
        case DIRECTION_RIGHT:
            ret_string = DIR_RIGHT;
            break;
        case DIRECTION_LEFT:
            ret_string = DIR_LEFT;
            break;
        default:
            ret_string = "no valid direction";
            break;
        }
        return ret_string;
    }
}
