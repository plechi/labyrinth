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
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.XMLReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.swing.JOptionPane;

/**
 * class seperate and parses instructions from a string input
 */
public class InstructionTxtParser {
    private String line_;

    private String condition_start_string_;
    private String condition_end_string_;
    private String action_start_string_;
    private String action_end_string_;
    private String condition_action_separator_string_;
    private String separator_string_;
    private String comment_out_line_string_;
    private String comment_out_start_string_;
    private String comment_out_end_string_;
    private String deactivate_instruction_string_;
    private String wall_string_;
    private String way_string_;
    private String direction_left_string_;
    private String direction_ahead_string_;
    private String direction_right_string_;
    private String direction_back_string_;
    private URL url_ = null;
    private String path_ = null;
    private InstructionTXTXMLContentHandler handler_;
    private boolean comment_out_start_ = false;
    private IntStringMap mark_array_, state_array_;

    // -----------------------------------------------------------------------------
    /**
     * Standard constructor parses the config xml file to get the settings
     * 
     * @param url
     */
    public InstructionTxtParser(URL url) {
        InstructionTXTXMLContentHandler handler_ = new InstructionTXTXMLContentHandler();
        try {
            path_ = (new URL(url, "src/config/InstructionTXT.xml")).toString();
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true); // neu
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            handler_ = new InstructionTXTXMLContentHandler();
            xmlReader.setContentHandler(handler_);
            XMLErrorHandler xml_error_handler = new XMLErrorHandler();
            xmlReader.setErrorHandler(xml_error_handler);
            xmlReader.parse(path_);
        } catch (SAXParseException exc) {
            System.out.println("ERROR");
            System.out.println("In File " + exc.getSystemId());
            System.out.println(exc.getMessage());
        } catch (SAXException exc) {
            System.out.println("SAXException");
            System.err.println(exc.getMessage());
            System.err.println(exc.toString());
        } catch (ParserConfigurationException exc) {
            System.out.println("ParserConfigurationException");
            System.err.println(exc.getMessage());
        } catch (IOException exc) {
            System.out.println("IOException");
            System.err.println(exc.getMessage());
        }

        condition_start_string_ = handler_.ConditionStartString();
        condition_end_string_ = handler_.ConditionEndString();
        action_start_string_ = handler_.ActionStartString();
        action_end_string_ = handler_.ActionEndString();
        condition_action_separator_string_ = handler_
                .ConditionActionSeparatorString();
        separator_string_ = handler_.SeparatorString();
        comment_out_line_string_ = handler_.CommentOutLineString();
        comment_out_start_string_ = handler_.CommentOutStartString();
        comment_out_end_string_ = handler_.CommentOutEndString();
        deactivate_instruction_string_ = handler_.DeactivateInstructionString();
        wall_string_ = handler_.WallString();
        way_string_ = handler_.WayString();
        direction_left_string_ = handler_.DirectionLeftString();
        direction_ahead_string_ = handler_.DirectionAheadString();
        direction_right_string_ = handler_.DirectionRightString();
        direction_back_string_ = handler_.DirectionBackString();
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
     * @return mark_array
     */
    public IntStringMap getMarkArray() {
        return mark_array_;
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
     * return the mark array
     * 
     * @return state_array
     */
    public IntStringMap getStateArray() {
        return state_array_;
    }

    // -----------------------------------------------------------------------------
    /**
     * checks the condtion string for syntax errors and generates a new
     * condition with the string informations
     * 
     * @param conditionstring
     * @param linecount
     * @return condition
     * @exception IllegalArgumentException
     *                thrown when a syntax error is detected
     */
    private Condition parseCondition(String condition, int linecount)
            throws IllegalArgumentException {
        Condition condi;
        boolean wall_left = false;
        boolean wall_right = false;
        boolean wall_ahead = false;
        String tmp_condition = condition;
        // parse Condition, seperate sensors, state and cell mark
        tmp_condition = tmp_condition.trim();
        int first_seperator = tmp_condition.indexOf(separator_string_);
        int second_seperator = tmp_condition.indexOf(separator_string_,
                first_seperator + 1);
        if (first_seperator == -1 || second_seperator == -1)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": to less arguments"));

        int third_seperator = tmp_condition.indexOf(separator_string_,
                second_seperator + 1);
        if (third_seperator != -1)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": to many arguments"));

        String condition_sensors = tmp_condition.substring(
                condition_start_string_.length(), first_seperator);
        condition_sensors = condition_sensors.trim();
        // System.out.println("Sensors found: "+condition_sensors);

        String condition_state = tmp_condition.substring(first_seperator
                + separator_string_.length(), second_seperator);
        condition_state = condition_state.trim();
        if (condition_state.length() > 30)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": state string is too long"));

        // System.out.println("State found: "+condition_state);
        String condition_cellmark = tmp_condition.substring(second_seperator
                + separator_string_.length(),
                tmp_condition.indexOf(condition_end_string_));
        condition_cellmark = condition_cellmark.trim();
        if (condition_cellmark.length() > 30)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": cellmark string is too long"));
        // System.out.println("Cellmark found: "+condition_cellmark);

        // parse Sensors,
        // left sensor
        if (condition_sensors.regionMatches(false, 0, wall_string_, 0,
                wall_string_.length()) == true) {
            wall_left = true;
            // System.out.println("wall_left");
            condition_sensors = condition_sensors.substring(wall_string_
                    .length());
        } else if (condition_sensors.regionMatches(false, 0, way_string_, 0,
                way_string_.length()) == true)
            condition_sensors = condition_sensors.substring(way_string_
                    .length());
        else if (condition_sensors.length() == 0)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter count for sensors"));
        else
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter for left sensor"));

        // head sensor
        condition_sensors = condition_sensors.trim();
        if (condition_sensors.regionMatches(false, 0, wall_string_, 0,
                wall_string_.length()) == true) {
            wall_ahead = true;
            // System.out.println("wall_ahead");
            condition_sensors = condition_sensors.substring(wall_string_
                    .length());
        } else if (condition_sensors.regionMatches(false, 0, way_string_, 0,
                way_string_.length()) == true)
            condition_sensors = condition_sensors.substring(way_string_
                    .length());
        else if (condition_sensors.length() == 0)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter count for sensors"));
        else
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter for ahead sensor"));

        // right sensor
        condition_sensors = condition_sensors.trim();
        if (condition_sensors.regionMatches(false, 0, wall_string_, 0,
                wall_string_.length()) == true) {
            wall_right = true;
            // System.out.println("wall_right");
            condition_sensors = condition_sensors.substring(wall_string_
                    .length());
        } else if (condition_sensors.regionMatches(false, 0, way_string_, 0,
                way_string_.length()) == true)
            condition_sensors = condition_sensors.substring(way_string_
                    .length());
        else if (condition_sensors.length() == 0)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter count for sensors"));
        else
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter for right sensor"));

        condition_sensors = condition_sensors.trim();
        if (condition_sensors.length() != 0)
            throw (new IllegalArgumentException(
                    "Condition Syntax Error in line " + linecount
                            + ": wrong parameter count for sensors"));

        return condi = new Condition(state_array_.setString(condition_state),
                mark_array_.setString(condition_cellmark), wall_left,
                wall_right, wall_ahead);
    }

    // -----------------------------------------------------------------------------
    /**
     * checks the action string for syntax errors and generates a new action
     * with the string informations
     * 
     * @param actionstring
     * @param linecount
     * @return action
     * @exception IllegalArgumentException
     *                thrown when a syntax error is detected
     */
    private Action parseAction(String action, int linecount)
            throws IllegalArgumentException {
        Action acti;
        int dir = 0;
        String tmp_action = action;

        // parse Action,
        tmp_action = tmp_action.trim();
        int first_seperator = tmp_action.indexOf(separator_string_);
        int second_seperator = tmp_action.indexOf(separator_string_,
                first_seperator + 1);
        if (first_seperator == -1 || second_seperator == -1)
            throw (new IllegalArgumentException("Action Syntax Error in line "
                    + linecount + ": to less arguments"));

        int third_seperator = tmp_action.indexOf(separator_string_,
                second_seperator + 1);
        if (third_seperator != -1)
            throw (new IllegalArgumentException("Action Syntax Error in line "
                    + linecount + ": to many arguments"));

        String action_cellmark = tmp_action.substring(
                action_start_string_.length(), first_seperator);
        action_cellmark = action_cellmark.trim();
        if (action_cellmark.length() > 30)
            throw (new IllegalArgumentException("Action Syntax Error in line "
                    + linecount + ": cellmark string is too long"));
        // System.out.println("action cellmark found: "+action_cellmark);

        String direction = tmp_action.substring(first_seperator
                + separator_string_.length(), second_seperator);
        direction = direction.trim();
        // System.out.println("Direction found: "+direction);

        String action_state = tmp_action.substring(second_seperator
                + separator_string_.length(),
                tmp_action.indexOf(action_end_string_));
        action_state = action_state.trim();
        if (action_state.length() > 30)
            throw (new IllegalArgumentException("Action Syntax Error in line "
                    + linecount + ": state string is too long"));
        // System.out.println("action state found: "+action_state);

        // parse Direction

        if ((direction.regionMatches(true, 0, direction_left_string_, 0,
                direction_left_string_.length()) == true)
                && (direction.length() == direction_left_string_.length()))
            dir = 4;
        else if ((direction.regionMatches(true, 0, direction_ahead_string_, 0,
                direction_ahead_string_.length()) == true)
                && (direction.length() == direction_ahead_string_.length()))
            dir = 1;
        else if ((direction.regionMatches(true, 0, direction_right_string_, 0,
                direction_right_string_.length()) == true)
                && (direction.length() == direction_right_string_.length()))
            dir = 2;
        else if ((direction.regionMatches(true, 0, direction_back_string_, 0,
                direction_back_string_.length()) == true)
                && (direction.length() == direction_back_string_.length()))
            dir = 3;
        else
            throw (new IllegalArgumentException("Action Syntax Error in line "
                    + linecount + ": wrong parameter for direction"));

        return acti = new Action(state_array_.setString(action_state),
                mark_array_.setString(action_cellmark), dir);

    }

    // -----------------------------------------------------------------------------
    /**
     * parses a line, checks if a comment out char was found, seperates action
     * and condition, and generates a instruction with the information about
     * condition and action
     * 
     * @param line
     * @param linecount
     * @return instruction
     * @exception IllegalArgumentException
     *                thrown when a syntax error is detected
     */
    private Instruction parseLine(String line, int linecount)
            throws IllegalArgumentException {
        String tmp_condition;
        String tmp_action;
        Action acti;
        Condition condi;

        Instruction instruction;
        line = line.trim();
        instruction = null;

        // ----------------------------------------------------------------------
        int comment_out = (line.indexOf(comment_out_start_string_));
        int comment_in = (line.indexOf(comment_out_end_string_));

        if ((comment_out != -1) && ((comment_in != -1))
                && (comment_out < comment_in)) {
            String tmp_before = line.substring(0, comment_out);
            String tmp_post = line.substring(comment_in
                    + comment_out_end_string_.length());
            tmp_before = tmp_before.trim();
            tmp_post = tmp_post.trim();
            line = tmp_before.concat(tmp_post);
        } else if (comment_out != -1) {
            line = line.substring(0, comment_out);
            line = line.trim();
            comment_out_start_ = true;
        } else if (comment_in != -1 && comment_out_start_ == true) {
            line = line.substring(comment_in
                    + (comment_out_end_string_.length()));
            comment_out_start_ = false;
        } else if (comment_out_start_ == true) {
            line = "";
        }

        int line_comment = line.indexOf(comment_out_line_string_);
        if (line_comment != -1)
            line = line.substring(0, line_comment);

        // ----------------------------------------------------------------------
        if (line.startsWith(comment_out_line_string_) || line.length() == 0) {
        } else {
            boolean aktive; // aktivierte Regel ?
            if (line.startsWith(deactivate_instruction_string_)) {
                aktive = false;
                int deaktivate = line.indexOf(deactivate_instruction_string_);
                if (deaktivate == -1)
                    throw (new IllegalArgumentException("Syntax Error in line "
                            + linecount));
                else {
                    line = line.substring(deaktivate
                            + deactivate_instruction_string_.length(),
                            line.length());
                    line = line.trim();
                }
            } else {
                aktive = true;
            }

            int cond_act_sep = line.indexOf(condition_action_separator_string_);
            if (cond_act_sep == -1)
                throw (new IllegalArgumentException("Syntax Error in line "
                        + linecount));
            else {
                tmp_condition = line.substring(0, cond_act_sep);
                tmp_condition = tmp_condition.trim();
                tmp_action = line.substring(cond_act_sep
                        + condition_action_separator_string_.length());
                tmp_action = tmp_action.trim();
            }

            // seperate Condition and Action
            if (tmp_condition.startsWith(condition_start_string_) == false)
                throw (new IllegalArgumentException("Syntax Error in line "
                        + linecount));

            if (tmp_condition.endsWith(condition_end_string_) == false)
                throw (new IllegalArgumentException("Syntax Error in line "
                        + linecount));

            if (tmp_action.startsWith(action_start_string_) == false)
                throw (new IllegalArgumentException("Syntax Error in line "
                        + linecount));

            if (tmp_action.endsWith(action_end_string_) == false)
                throw (new IllegalArgumentException("Syntax Error in line "
                        + linecount));

            try {
                condi = parseCondition(tmp_condition, linecount);
                acti = parseAction(tmp_action, linecount);
                instruction = new Instruction(condi, acti, 0, aktive); // --------HACK!!!!------ALLE-INSTRUCTIONS-MIT-ID-0----
            } catch (IllegalArgumentException exc) {
                System.err.println(exc.getMessage());
                JOptionPane error;
                error = new JOptionPane();
                error.showMessageDialog(null, exc.getMessage(),
                        "Marvin-10 Programmfehler", error.ERROR_MESSAGE);
            }
        }
        return instruction;
    }

    // -----------------------------------------------------------------------------
    /**
     * parses a string, seperates lines, and generates a instruction list
     * 
     * @param string
     * @return instructionlist
     * @exception IllegalArgumentException
     *                thrown when a syntax error is detected
     */
    public InstructionList parseText(String textstring) {
        String text = textstring;
        InstructionList instructions;
        instructions = new InstructionList();
        int linecount = 0;
        int line_end = 0;
        int line_start = 0;

        try {
            if (text.endsWith("\n") == false)
                text = text.concat("\n");

            line_end = text.indexOf("\n", line_end);
            while (line_end != -1) {
                linecount++;
                String line = "";
                line = text.substring(line_start, line_end);
                line = line.trim();
                line_start = line_end;
                line_end++;
                if (line.length() != 0) {
                    Instruction new_instruction;
                    new_instruction = parseLine(line, linecount);
                    if (new_instruction != null)
                        instructions.addInstruction(new_instruction);
                }
                line_end = text.indexOf("\n", line_end);
            }
        } catch (IllegalArgumentException exc) {
            System.err.println(exc.getMessage());
            JOptionPane error;
            error = new JOptionPane();
            error.showMessageDialog(null, exc.getMessage(),
                    "Marvin-10 Programmfehler", error.ERROR_MESSAGE);
        }
        return instructions;
    }

}
