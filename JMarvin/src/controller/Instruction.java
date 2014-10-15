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
 * class representing an Instruction which consists of one Condition and one
 * Action.
 */
public class Instruction {
    private Condition condition_;
    private Action action_;
    private int id_;
    private boolean aktive_;

    // -----------------------------------------------------------------------------
    /**
     * constructor generates a new instruction with the specified arguments
     * 
     * @param condition
     * @param action
     * @param id
     */
    /*
     * public Instruction(Condition condition, Action action) { condition_ =
     * condition; action_ = action; }
     */

    public Instruction(Condition condition, Action action, int id,
            boolean aktive) {
        condition_ = condition;
        action_ = action;
        id_ = id;
        aktive_ = aktive;
    }

    public Instruction(int id) {
        condition_ = new Condition();
        action_ = new Action();
        id_ = id;
        aktive_ = true;
    }

    public Instruction(Instruction instruction) {
        condition_ = new Condition(instruction.getCondition());
        action_ = new Action(instruction.getAction());
    }

    // -----------------------------------------------------------------------------
    /**
     * method returns the id of the instruction
     * 
     * @return true if they are equal
     */
    public int getId() {
        return id_;
    }

    // -----------------------------------------------------------------------------
    /**
     * method returns if the instruction is aktive
     * 
     * @return true if the instruction is aktive
     */
    public boolean isAktive() {
        return aktive_;
    }

    // -----------------------------------------------------------------------------
    /**
     * method allows to aktivate/deactivate the instruction
     * 
     * @param aktive
     *            true if instruction should be aktive
     */
    public void setAktive(boolean aktive) {
        aktive_ = aktive;
    }

    // -----------------------------------------------------------------------------
    /**
     * method updates the id of the instruction
     * 
     * @param id
     */
    public void updateId(int id) {
        id_ = id;
    }

    // -----------------------------------------------------------------------------
    /**
     * method compares 2 instructions and returns true if they are equal.
     * 
     * @param instruction
     * @return true if they are equal
     */
    public boolean equals(Instruction instruction) {
        if (condition_.equals(instruction.getCondition())
                && action_.equals(instruction.getAction()))
            return true;
        else
            return false;
    }

    // -----------------------------------------------------------------------------
    /**
     * sets the condition
     * 
     * @param condition
     */
    public void setCondition(Condition condition) {
        condition_ = condition;
    }

    // -----------------------------------------------------------------------------
    /**
     * sets the action
     * 
     * @param action
     */
    public void setAction(Action action) {
        action_ = action;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the condition
     * 
     * @return condition
     */
    public Condition getCondition() {
        return condition_;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the action
     * 
     * @return action
     */
    public Action getAction() {
        return action_;
    }

    // -----------------------------------------------------------------------------
    /**
     * toString method for debugging
     * 
     * @return info about the instruction in string-format
     */
    public String toString() {
        String newline = System.getProperty("line.separator");
        String instruction = new String();
        if (aktive_ == true)
            instruction = ("ID: " + id_ + "  (" + condition_.toString() + ")"
                    + "->" + "(" + action_.toString() + ")" + newline);
        else
            instruction = ("ID: " + id_ + " #(" + condition_.toString() + ")"
                    + "->" + "(" + action_.toString() + ")" + newline);
        return instruction;
    }

}
