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

import java.awt.*;
import java.net.URL;

/**
 * class representing an rule xpert mode
 */
public class InstructionXpertMode {
    URL url_;
    TextArea ta_;
    Instruction instruction_;
    IntStringMap mark_array_, state_array_;

    // -----------------------------------------------------------------------------
    /**
     * Standard constructor
     */
    public InstructionXpertMode() {
    }

    // -----------------------------------------------------------------------------
    /**
     * set the base of the document.
     */
    public void setUrl(URL url) {
        url_ = url;
    }

    // -----------------------------------------------------------------------------

    /**
     * return the mark array
     *
     * @return mark_array
     */
    public IntStringMap getMarkArray() {
        return mark_array_;
    }

    // -----------------------------------------------------------------------------

    /**
     * set a mark array to map the input strings to integers
     *
     * @param mark_array
     */
    public void setMarkArray(IntStringMap mark_array) {
        mark_array_ = mark_array;
    }

    // -----------------------------------------------------------------------------

    /**
     * return the mark array
     *
     * @return state_array
     */
    public IntStringMap getStateArray() {
        return state_array_;
    }

    // -----------------------------------------------------------------------------

    /**
     * set a state array to map the input strings to integers
     *
     * @param mark_array
     */
    public void setStateArray(IntStringMap state_array) {
        state_array_ = state_array;
    }

    // -----------------------------------------------------------------------------

    /**
     * prints the rule list to the txt editor
     */
    public void outputRuleList(InstructionList list, TextArea ta) {
        InstructionTxtWriter writer_ = new InstructionTxtWriter(url_);
        writer_.setMarkArray(mark_array_);
        writer_.setStateArray(state_array_);
        String text = writer_.writeText(list);
        ta.setText(text);
    }

    // -----------------------------------------------------------------------------
    /**
     * read the rule list from txt editor
     */
    public InstructionList getRuleList(TextArea ta) {
        InstructionList instructions;
        instructions = new InstructionList();
        String code;
        code = ta.getText();

        InstructionTxtParser parser_ = new InstructionTxtParser(url_);
        parser_.setMarkArray(mark_array_);
        parser_.setStateArray(state_array_);
        instructions = parser_.parseText(code);
        mark_array_ = parser_.getMarkArray();
        state_array_ = parser_.getStateArray();
        return instructions;
    }
}