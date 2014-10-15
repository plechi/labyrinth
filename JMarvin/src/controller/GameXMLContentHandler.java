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
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.lang.Integer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedList;

/**
 * class parse the input from game.xml
 */
public class GameXMLContentHandler extends DefaultHandler {
    private String wall_picture_;
    private String way_picture_;
    private String robot_picture_;
    private String diamond_picture_;
    private String start_picture_;
    private int num_levels_;
    private int number_;
    private LinkedList file_;
    private LinkedList instruction_file_;
    private LinkedList level_name_;
    private String current_tag_;
    private URL document_base_;

    // -----------------------------------------------------------------------------
    /**
     * constructor generates a new Game XML Content Handler
     */
    public GameXMLContentHandler() {
        document_base_ = null;
        wall_picture_ = new String();
        way_picture_ = new String();
        robot_picture_ = new String();
        diamond_picture_ = new String();
        start_picture_ = new String();
        num_levels_ = 0;
        current_tag_ = new String();
        file_ = new LinkedList();
        instruction_file_ = new LinkedList();
        level_name_ = new LinkedList();
    }

    // -----------------------------------------------------------------------------
    /**
     * method sets the document base
     * 
     * @param document_base
     */
    public void setDocumentBase(URL document_base) {
        document_base_ = document_base;
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
        if (current_tag_.equals(new String(TagName.LEVEL))) {
            num_levels_++;
            file_.add(num_levels_ - 1, null);
            instruction_file_.add(num_levels_ - 1, null);
            level_name_.add(num_levels_ - 1, null);
        }
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

            if (temp_string.length() < 1)
                throw new IllegalArgumentException(new String("\"<"
                        + current_tag_ + ">\"( " + num_levels_
                        + " ) has not a valid argument "));

            else if (current_tag_.equals(new String(TagName.WALLPICTURE))) {
                wall_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.WAYPICTURE))) {
                way_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.ROBOTPICTURE))) {
                robot_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.DIAMONDPICTURE))) {
                diamond_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.STARTPICTURE))) {
                start_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.FILE))) {
                try {
                    URL url = new URL(document_base_, temp_string);
                    String file = url.toString();
                    file_.set(num_levels_ - 1, file);
                } catch (MalformedURLException exc) {
                    System.err.println(exc.getMessage());
                }
            }

            else if (current_tag_.equals(new String(TagName.INSTRUCTIONLIST))) {
                try {
                    URL url = new URL(document_base_, temp_string);
                    String file = url.toString();
                    instruction_file_.set(num_levels_ - 1, file);
                } catch (MalformedURLException exc) {
                    System.err.println(exc.getMessage());
                }
            }

            else if (current_tag_.equals(new String(TagName.NAME))) {
                level_name_.set(num_levels_ - 1, temp_string);
            }
        }
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of the end of an element.
     * 
     * @param localName
     *            - The local name (without prefix), or the empty string if
     *            Namespace processing is not being performed.
     * @param qName
     *            - The qualified XML 1.0 name (with prefix), or the empty
     *            string if qualified names are not available.
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
     * find out the wall picture
     * 
     * @return wall picture
     */
    public String getWallPicture() {
        return wall_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the way picture
     * 
     * @return way picture
     */
    public String getWayPicture() {
        return way_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the robot picture
     * 
     * @return robot picture
     */
    public String getRobotPicture() {
        return robot_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the diamond picture
     * 
     * @return diamond picture
     */
    public String getDiamondPicture() {
        return diamond_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the start picture
     * 
     * @return start picture
     */
    public String getStartPicture() {
        return start_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * find out how many levels the game has
     * 
     * @return num levels
     */
    public int getNumLevels() {
        return num_levels_;
    }

    // -----------------------------------------------------------------------------
    /**
     * get the filename of a definite level
     * 
     * @param number
     * @return filename
     */
    public String getFile(int number) {
        if (number <= num_levels_ && number > 0) {
            return ((String) file_.get(number - 1));
        } else {
            System.out.println("ERROR getFile: " + number);
            return null;
        }
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the name of all levels
     * 
     * @return list of levelnames
     */
    public LinkedList getLevelNameList() {
        return (level_name_);
    }

    // -----------------------------------------------------------------------------
    /**
     * find out the instruction file names
     * 
     * @return list of instruction file names
     */
    public LinkedList getInstructionFileList() {
        return (instruction_file_);
    }
}
