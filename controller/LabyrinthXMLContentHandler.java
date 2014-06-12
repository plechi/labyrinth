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
import org.xml.sax.helpers.LocatorImpl;
import org.xml.sax.SAXParseException;
import java.util.HashMap;

/**
 * LabyrinthXMLContentHandler - XML Content Handler for generating the labyrinth
 * stored in an XML file
 */
public class LabyrinthXMLContentHandler extends DefaultHandler {

    private String temp_labyrinth_data_;
    private String current_tag_;

    private String level_name_;
    // Labyrinth Dimension
    private int width_;
    private int height_;
    // Load Labyrinth Options
    private boolean is_load_labyrinth_;
    private char start_symbol_;
    private char wall_symbol_;
    private char diamond_symbol_;
    private char way_symbol_;

    private String char_symbol_;
    private String string_symbol_;

    private Direction starting_direction_;
    private char[][] labyrinth_data_;
    // Create Labyrinth Options
    private boolean is_create_labyrinth_;
    private int diamonds_;
    private int way_cells_;
    private int min_way_lenght_;
    private int max_way_lenght_;
    private int build_angles_;
    private int build_trees_;
    private int build_loops_;
    private int build_places_;
    // Style Options
    private boolean is_style_;
    private boolean labyrinth_field_;
    private boolean is_instruction_list_;
    private String wall_picture_;
    private String way_picture_;
    private String robot_picture_;
    private String diamond_picture_;
    private String start_picture_;
    private String instruction_file_name_;
    private URL document_base_;
    private String document_;
    private boolean is_error_;
    private HashMap char_symbols_map_, string_symbols_map_;

    // -----------------------------------------------------------------------------
    /**
     * Standard constructor
     */
    public LabyrinthXMLContentHandler() {
        document_base_ = null;
        width_ = 0;
        height_ = 0;
        start_symbol_ = '\n';
        wall_symbol_ = '\n';
        diamond_symbol_ = '\n';
        way_symbol_ = '\n';
        starting_direction_ = new Direction();
        temp_labyrinth_data_ = new String();
        current_tag_ = new String();
        level_name_ = new String();
        diamonds_ = 0;
        way_cells_ = 0;
        min_way_lenght_ = 0;
        max_way_lenght_ = 0;
        build_angles_ = 0;
        build_trees_ = 0;
        build_loops_ = 0;
        build_places_ = 0;
        is_load_labyrinth_ = false;
        is_create_labyrinth_ = false;
        is_style_ = false;
        labyrinth_field_ = false;
        is_instruction_list_ = false;
        is_error_ = false;
        char_symbols_map_ = new HashMap();
        string_symbols_map_ = new HashMap();
    }

    // -----------------------------------------------------------------------------
    /**
     * set the base of the document.
     */
    public void setDocumentBase(URL document_base) {
        document_base_ = document_base;
    }

    // -----------------------------------------------------------------------------
    /**
     * set the name of the file to parse
     */
    public void setDocument(String document) {
        document_ = document;
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
        if (current_tag_.equals(new String(TagName.LABYRINTH))) {
            is_load_labyrinth_ = true;
        }

        else if (current_tag_.equals(new String(TagName.CREATE))) {
            is_create_labyrinth_ = true;
        }

        else if (current_tag_.equals(new String(TagName.STYLE))) {
            is_style_ = true;
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

            if (current_tag_.equals(new String(TagName.NAME))) {
                level_name_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.WIDTH))) {
                width_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.HEIGHT))) {
                height_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.STARTSYMBOL))) {
                start_symbol_ = temp_string.trim().charAt(0);
            }

            else if (current_tag_.equals(new String(TagName.WALLSYMBOL))) {
                wall_symbol_ = temp_string.charAt(0);
            }

            else if (current_tag_.equals(new String(TagName.DIAMONDSYMBOL))) {
                diamond_symbol_ = temp_string.charAt(0);
            }

            else if (current_tag_.equals(new String(TagName.WAYSYMBOL))) {
                way_symbol_ = temp_string.charAt(0);
            }

            else if (current_tag_.equals(new String(TagName.ZEROSYMBOL))) {
                char_symbols_map_.put(new String(temp_string.substring(0, 1)),
                        new String("0"));
                string_symbols_map_.put(new String("0"),
                        new String(temp_string.substring(0, 1)));
                // if(temp_string.length()!=1)
                // is_error_=true;
            }

            else if (current_tag_.equals(new String(TagName.ONESYMBOL))) {
                char_symbols_map_.put(new String(temp_string.substring(0, 1)),
                        new String("1"));
                string_symbols_map_.put(new String("1"),
                        new String(temp_string.substring(0, 1)));
                // if(temp_string.length()!=1)
                // is_error_=true;
            }

            else if (current_tag_.equals(new String(TagName.TWOSYMBOL))) {
                char_symbols_map_.put(new String(temp_string.substring(0, 1)),
                        new String("2"));
                string_symbols_map_.put(new String("2"),
                        new String(temp_string.substring(0, 1)));
                // if(temp_string.length()!=1)
                // is_error_=true;
            }

            else if (current_tag_.equals(new String(TagName.THREESYMBOL))) {
                char_symbols_map_.put(new String(temp_string.substring(0, 1)),
                        new String("3"));
                string_symbols_map_.put(new String("3"),
                        new String(temp_string.substring(0, 1)));
                // if(temp_string.length()!=1)
                // is_error_=true;
            } else if (current_tag_.equals(new String(TagName.CHARSYMBOL))) {
                char_symbol_ = new String(temp_string.substring(0, 1));
                // if(temp_string.length()!=1)
                // is_error_=true;
            } else if (current_tag_.equals(new String(TagName.STRINGSYMBOL))) {
                string_symbol_ = new String(temp_string);
            }

            else if (current_tag_.equals(new String(TagName.STARTDIRECTION))) {
                try {
                    if (temp_string.equals(new String(Direction.DIR_UP))) {
                        starting_direction_
                                .setDirection(Direction.DIRECTION_UP);
                    } else if (temp_string.equals(new String(
                            Direction.DIR_RIGHT))) {
                        starting_direction_
                                .setDirection(Direction.DIRECTION_RIGHT);
                    } else if (temp_string
                            .equals(new String(Direction.DIR_DOWN))) {
                        starting_direction_
                                .setDirection(Direction.DIRECTION_DOWN);
                    } else if (temp_string
                            .equals(new String(Direction.DIR_LEFT))) {
                        starting_direction_
                                .setDirection(Direction.DIRECTION_LEFT);
                    }
                } catch (IllegalDirectionException exc) {

                }
            }

            else if (current_tag_.equals(new String(TagName.LABYRINTHFIELD))) {
                labyrinth_field_ = true;
                // line_number_=;
                if (temp_string.length() > 1) {
                    temp_labyrinth_data_ = temp_labyrinth_data_ + temp_string
                            + "\n";
                }
            }

            else if (current_tag_.equals(new String(TagName.DIAMONDS))) {
                diamonds_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.WAYCELLS))) {
                way_cells_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.MINWAYLENGHT))) {
                min_way_lenght_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.MAXWAYLENGHT))) {
                max_way_lenght_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.BUILDANGLES))) {
                build_angles_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.BUILDTREES))) {
                build_trees_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.BUILDLOOPS))) {
                build_loops_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.BUILDPLACES))) {
                build_places_ = Integer.decode(temp_string).intValue();
            }

            else if (current_tag_.equals(new String(TagName.WALLPICTURE))) {
                wall_picture_ = new String();
                wall_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.WAYPICTURE))) {
                way_picture_ = new String();
                way_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.ROBOTPICTURE))) {
                robot_picture_ = new String();
                robot_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.DIAMONDPICTURE))) {
                diamond_picture_ = new String();
                diamond_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.STARTPICTURE))) {
                start_picture_ = new String();
                start_picture_ = temp_string;
            }

            else if (current_tag_.equals(new String(TagName.INSTRUCTIONFILE))) {
                try {
                    URL new_url = new URL(document_base_, temp_string);
                    // URI uri = new URI(new_url.toString());

                    // File file = new File(uri);
                    String instruction_file_name_ = new_url.toString();
                    is_instruction_list_ = true;
                } catch (MalformedURLException ex) {
                    System.err.println(ex.getMessage());
                }
                /*
                 * catch( URISyntaxException ex ) {
                 * System.err.println(ex.getMessage()); }
                 */
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
        if (qualName.equals(new String(TagName.SYMBOL))) {
            char_symbols_map_.put(new String(char_symbol_), new String(
                    string_symbol_));
            string_symbols_map_.put(new String(string_symbol_), new String(
                    char_symbol_));
            char_symbol_ = "";
            string_symbol_ = "";
        }
        current_tag_ = "";
    }

    // -----------------------------------------------------------------------------
    /**
     * Receive notification of the end of the document.
     */
    public void endDocument() throws SAXParseException {
        if (labyrinth_field_ == true)
            convertLabyrinthData();
    }

    // -----------------------------------------------------------------------------
    /**
     * Generates a twodimensional character-array which is an ASCII
     * representation of the labyrinthfield.
     */
    public void convertLabyrinthData() throws SAXParseException {
        // System.out.println(temp_labyrinth_data_.length() + "==" +
        // (height_*width_+height_));
        // if(temp_labyrinth_data_.length()==height_*width_+height_)
        // {
        labyrinth_data_ = new char[height_][width_];

        String[] rows = temp_labyrinth_data_.split("\n");

        for (int row_count = 0; row_count < height_; row_count++) {
            temp_labyrinth_data_ = rows[row_count].trim();
            for (int col_count = 0; col_count < width_; col_count++) {
                labyrinth_data_[row_count][col_count] = temp_labyrinth_data_
                        .charAt(col_count);
            }
        }
        /*
         * } else { try { //System.out.println("ERROR: false size"); String
         * message = new String("\"<"+TagName.LABYRINTHFIELD) +
         * ">\" has a wrong size"; LocatorImpl locator = new LocatorImpl();
         * //locator.setLineNumber(line_number_); URL new_url = new
         * URL(document_base_,document_);
         * locator.setSystemId(new_url.toString()); is_error_=true; throw (new
         * SAXParseException(message, locator)); } catch( MalformedURLException
         * ex ) { System.err.println(ex.getMessage()); } }
         */
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for errors
     * 
     * @return true, if a error occurred
     */
    public boolean isXMLError() {
        return is_error_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the level name
     * 
     * @return level name
     */
    public String getLevelName() {
        return level_name_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the labyrinth width
     * 
     * @return width
     */
    public int getWidth() {
        return width_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the labyrinth height
     * 
     * @return height
     */
    public int getHeight() {
        return height_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite start symbol
     * 
     * @return start_symbol
     */
    public char getStartSymbol() {
        return start_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite wall symbol
     * 
     * @return wall_symbol
     */
    public char getWallSymbol() {
        return wall_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite diamond symbol
     * 
     * @return diamond_symbol
     */
    public char getDiamondSymbol() {
        return diamond_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite way symbol
     * 
     * @return way_symbol
     */
    public char getWaySymbol() {
        return way_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite zero symbol
     * 
     * @return zero_symbol
     */
    public char getZeroSymbol() {
        Character tmp;
        if (char_symbols_map_.containsKey("0"))
            tmp = ((String) char_symbols_map_.get("0")).charAt(0);
        else
            tmp = '\n';
        return tmp;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite one symbol
     * 
     * @return one_symbol
     */
    public char getOneSymbol() {
        Character tmp;
        if (char_symbols_map_.containsKey("1"))
            tmp = ((String) char_symbols_map_.get("1")).charAt(0);
        else
            tmp = '\n';
        return tmp;
        // return one_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite two symbol
     * 
     * @return two_symbol
     */
    public char getTwoSymbol() {
        Character tmp;
        if (char_symbols_map_.containsKey("2"))
            tmp = ((String) char_symbols_map_.get("2")).charAt(0);
        else
            tmp = '\n';
        return tmp;
        // return two_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite three symbol
     * 
     * @return three_symbol
     */
    public char getThreeSymbol() {
        Character tmp;
        if (char_symbols_map_.containsKey("3"))
            tmp = ((String) char_symbols_map_.get("3")).charAt(0);
        else
            tmp = '\n';
        return tmp;
        // return three_symbol_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite symbol map charecter as key
     * 
     * @return symbol map
     */
    public HashMap getSymboleMap() {
        return char_symbols_map_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite symbol map string as key
     * 
     * @return symbol map
     */
    public HashMap getStringSymboleMap() {
        return string_symbols_map_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite start direction
     * 
     * @return start direction
     */
    public Direction getStartingDirection() {
        return starting_direction_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Return the labyrinth field
     * 
     * @return labyrinth_data
     */
    public char[][] getLabyrinth() {
        return labyrinth_data_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks for the number of diamonds in the labyrinth
     * 
     * @return diamonds
     */
    public int getNumDiamonds() {
        return diamonds_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks for the number of way cells in the labyrinth
     * 
     * @return way_cells
     */
    public int getNumWayCells() {
        return way_cells_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks for the number min. way lenght
     * 
     * @return min_way_cells
     */
    public int getMinWayLenght() {
        return min_way_lenght_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks for the number max. way lenght
     * 
     * @return max_way_cells
     */
    public int getMaxWayLenght() {
        return max_way_lenght_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks in building angles is allowed
     * 
     * @return building_angles >0, angles are allowed
     */
    public int getBuildAngles() {
        return build_angles_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks in building trees is allowed
     * 
     * @return building_trees >0, trees are allowed
     */
    public int getBuildTrees() {
        return build_trees_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks in building places is allowed
     * 
     * @return building_places >0, places are allowed
     */
    public int getBuildPlaces() {
        return build_places_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Asks in building loops is allowed
     * 
     * @return building_loops >0 is the count of max. loops to build
     */
    public int getBuildLoops() {
        return build_loops_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite wall picture
     * 
     * @return wall_picture
     */
    public String getWallPicture() {
        return wall_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite way picture
     * 
     * @return way picture
     */
    public String getWayPicture() {
        return way_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite robot picture
     * 
     * @return robot_picture
     */
    public String getRobotPicture() {
        return robot_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite diamond picture
     * 
     * @return diamond_picture
     */
    public String getDiamondPicture() {
        return diamond_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the definite start picture
     * 
     * @return start_picture
     */
    public String getStartPicture() {
        return start_picture_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes if the labyrinth is defined in the xml file
     * 
     * @return true, if yes
     */
    public boolean isLoadLabyrinth() {
        return is_load_labyrinth_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes if the labyrinth is to generate automaticly
     * 
     * @return true, if yes
     */
    public boolean isCreateLabyrinth() {
        return is_create_labyrinth_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes if a specifically style for the labyrinth is set
     * 
     * @return true, if yes
     */
    public boolean isStyle() {
        return is_style_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes if a specifically instruction list in defined
     * 
     * @return true, if yes
     */
    public boolean isInstructionList() {
        return is_instruction_list_;
    }

    // -----------------------------------------------------------------------------
    /**
     * Askes for the instruction list file name
     * 
     * @return file_name
     */
    public String getInstructionFileName() {
        return instruction_file_name_;
    }
}
