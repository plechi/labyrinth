package controller;

/*Copyright 2004-2006 Univ.Prof. Dipl.-Ing. Dr.techn. Wolfgang SLANY,
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
import java.applet.AppletContext;

public class TUGInformations {

    static private URL document_base_;
    static private URL code_base_;
    static private URL game_document_base_;
    static private AppletContext context_;
    static private String game_file_name_;

    static public void setDocumentBase(URL document_base) {
        document_base_ = document_base;
    }

    static public URL getDocumentBase() {
        return document_base_;
    }

    static public void setCodeBase(URL code_base) {
        code_base_ = code_base;
    }

    static public URL getCodeBase() {
        return code_base_;
    }

    static public void setGameDocumentBase(URL game_document_base) {
        game_document_base_ = game_document_base;
    }

    static public URL getGameDocumentBase() {
        return game_document_base_;
    }

    static public void setAppletContext(AppletContext context) {
        context_ = context;
    }

    static public AppletContext getAppletContext() {
        return context_;
    }

    static public void addGameFileName(String name) {
        game_file_name_ = name;
    }

    static public String getGameFileName() {
        return game_file_name_;
    }
}