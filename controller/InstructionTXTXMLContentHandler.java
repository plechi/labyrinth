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
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * InstructionListXMLContentHandler - XML Content Handler for instruction list
 * stored in an XML file
 * 
 * 
 */
public class InstructionTXTXMLContentHandler extends DefaultHandler {

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

    private String current_tag_;
    private URL document_base_;
    private String document_;

    // -----------------------------------------------------------------------------
    /**
     * Standard constructor
     */
    public InstructionTXTXMLContentHandler() {
        condition_start_string_ = new String();
        condition_end_string_ = new String();
        action_start_string_ = new String();
        action_end_string_ = new String();
        condition_action_separator_string_ = new String();
        separator_string_ = new String();
        comment_out_line_string_ = new String();
        comment_out_start_string_ = new String();
        comment_out_end_string_ = new String();
        deactivate_instruction_string_ = new String();
        wall_string_ = new String();
        way_string_ = new String();
        direction_left_string_ = new String();
        direction_ahead_string_ = new String();
        direction_right_string_ = new String();
        direction_back_string_ = new String();

        current_tag_ = new String();
        document_base_ = null;
        document_ = null;
    }

    // -----------------------------------------------------------------------------
    /**
     * set the base of the document.
     */
    public void setDocumentBase(URL document_base) {
        document_base_ = document_base;
        System.out.println("url");
        System.out.println(document_base_);
    }

    // -----------------------------------------------------------------------------
    /**
     * set the file name of the document.
     */
    public void setDocument(String document) {
        document_ = document;
        System.out.println("file");
        System.out.println(document_);
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of the beginning of the document.
     */
    public void startDocument() {
        // nothing to do here.
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of the start of an element.
     * 
     * @param attributes
     *            - The specified or defaulted attributes.
     * @param localName
     *            - The local name (without prefix), or the empty string if
     *            Namespace processing is not being performed.
     * @param qualName
     *            - The qualified name (with prefix), or the empty string if
     *            qualified names are not available.
     */
    public void startElement(String uri, String localName, String qualName,
            Attributes attribs) {
        current_tag_ = qualName;
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of character data inside an element.
     * 
     * @param charArray
     *            - The characters.
     * @param start
     *            - The start position in the character array.
     * @param length
     *            - The number of characters to use from the character array.
     */
    public void characters(char[] charArray, int start, int length) {
        if (current_tag_.length() > 0) {
            String temp_string = new String(charArray, start, length);
            temp_string = temp_string.trim();

            if (current_tag_.equals(new String(TagName.CONDITIONSTARTSTRING))) {
                condition_start_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.CONDITIONENDSTRING))) {
                condition_end_string_ = temp_string;
            } else if (current_tag_
                    .equals(new String(TagName.ACTIONSTARTSTRING))) {
                action_start_string_ = temp_string;
            } else if (current_tag_.equals(new String(TagName.ACTIONENDSTRING))) {
                action_end_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.CONDITIONACTIONSEPERATORSTRING))) {
                condition_action_separator_string_ = temp_string;
            } else if (current_tag_.equals(new String(TagName.SEPERATORSTRING))) {
                separator_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.COMMENTOUTLINESTRING))) {
                comment_out_line_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.COMMENTOUTSTARTSTRING))) {
                comment_out_start_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.COMMENTOUTENDSTRING))) {
                comment_out_end_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.DEACTIVATEINSTRUCTIONSTRING))) {
                deactivate_instruction_string_ = temp_string;
            } else if (current_tag_.equals(new String(TagName.WALLSTRING))) {
                wall_string_ = temp_string;
            } else if (current_tag_.equals(new String(TagName.WAYSTRING))) {
                way_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.DIRECTIONLEFTSTRING))) {
                direction_left_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.DIRECTIONAHEADSTRING))) {
                direction_ahead_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.DIRECTIONRIGHTSTRING))) {
                direction_right_string_ = temp_string;
            } else if (current_tag_.equals(new String(
                    TagName.DIRECTIONBACKSTRING))) {
                direction_back_string_ = temp_string;
            }
        }
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of the end of an element.
     * 
     * @param attributes
     *            - The specified or defaulted attributes.
     * @param localName
     *            - The local name (without prefix), or the empty string if
     *            Namespace processing is not being performed.
     * @param qualName
     *            - The qualified name (with prefix), or the empty string if
     *            qualified names are not available.
     */
    public void endElement(String uri, String localName, String qualName) {
        current_tag_ = "";
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of the end of the document.
     */
    public void endDocument() {

    }

    // -----------------------------------------------------------------------------
    /**
     * get the condition start string.
     * 
     * @return condition start string
     */
    public String ConditionStartString() {
        return condition_start_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the condition end string.
     * 
     * @return condition end string
     */
    public String ConditionEndString() {
        return condition_end_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the action start string.
     * 
     * @return action start string
     */
    public String ActionStartString() {
        return action_start_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the action end string.
     * 
     * @return action end string
     */
    public String ActionEndString() {
        return action_end_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the seperator string between condition and action.
     * 
     * @return condition action separator string
     */
    public String ConditionActionSeparatorString() {
        return condition_action_separator_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the seperator string.
     * 
     * @return separator string
     */
    public String SeparatorString() {
        return separator_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string to comment out one line.
     * 
     * @return comment out line string
     */
    public String CommentOutLineString() {
        return comment_out_line_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string for starting comment out.
     * 
     * @return comment out start string
     */
    public String CommentOutStartString() {
        return comment_out_start_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the end string for comment out.
     * 
     * @return comment out end string
     */
    public String CommentOutEndString() {
        return comment_out_end_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string to deaktivate a instruction.
     * 
     * @return comment out end string
     */
    public String DeactivateInstructionString() {
        return deactivate_instruction_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string representing a wall.
     * 
     * @return wall string
     */
    public String WallString() {
        return wall_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string representing a way.
     * 
     * @return way string
     */
    public String WayString() {
        return way_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string representing go left.
     * 
     * @return direction left string
     */
    public String DirectionLeftString() {
        return direction_left_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string representing go ahead.
     * 
     * @return direction ahead string
     */
    public String DirectionAheadString() {
        return direction_ahead_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string representing go right.
     * 
     * @return direction right string
     */
    public String DirectionRightString() {
        return direction_right_string_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the string representing go back.
     * 
     * @return direction back string
     */
    public String DirectionBackString() {
        return direction_back_string_;
    }
}
