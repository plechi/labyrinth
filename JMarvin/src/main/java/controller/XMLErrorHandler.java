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

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLErrorHandler extends DefaultHandler {
    private boolean is_error_;

    // -----------------------------------------------------------------------------
    /**
     * standard constructor
     */
    public XMLErrorHandler() {
        is_error_ = false;
    }

    // -----------------------------------------------------------------------------
    /**
     * define the XML warning, sets is_error true
     * 
     * @param SAXParseException
     */
    public void warning(SAXParseException e) {
        System.out.println("WARNING");
        System.out.println("In File " + e.getSystemId());
        System.out.println("In Line " + e.getLineNumber() + " "
                + e.getMessage());
        ;
        is_error_ = true;
    }

    // -----------------------------------------------------------------------------
    /**
     * define the XML error, sets is_error true
     * 
     * @param SAXParseException
     */
    public void error(SAXParseException e) {
        System.out.println("ERROR");
        System.out.println("In File " + e.getSystemId());
        System.out.println("In Line " + e.getLineNumber() + " "
                + e.getMessage());
        is_error_ = true;
    }

    // -----------------------------------------------------------------------------
    /**
     * define the XML fatal error, sets is_error true
     * 
     * @param SAXParseException
     */
    public void fatalError(SAXParseException e) {
        System.out.println("FATAL ERROR");
        System.out.println("In File " + e.getSystemId());
        System.out.println("In Line " + e.getLineNumber() + " "
                + e.getMessage());
        is_error_ = true;
    }

    // -----------------------------------------------------------------------------
    /**
     * asks if a error was detected
     * 
     * @return true, if a error was found
     */
    public boolean isXMLError() {
        return is_error_;
    }

}
