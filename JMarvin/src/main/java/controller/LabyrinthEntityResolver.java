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

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LabyrinthEntityResolver implements EntityResolver {
    private URL document_base_;

    public LabyrinthEntityResolver() {
        URL document_base = (URL) TUGInformations.getGameDocumentBase();
        document_base_ = document_base;
        System.out.println("LabyrinthEntityResolver("
                + document_base.toString() + ")");
    }

    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException, MalformedURLException {
       /* System.out
                .println("resolveEntity (" + publicId + ", " + systemId + ")");

        if (systemId.endsWith("/LabyrinthLevel.dtd"))
            systemId = "########LabyrinthLevel.dtd";

        URL new_url;
        try {
            new_url = new URL(document_base_, systemId.substring(8));
            System.out.println("change URL from " + systemId + " to "
                    + new_url.openStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(LabyrinthEntityResolver.class.getResourceAsStream("/resources/config/games/LabyrinthLevel.dtd")));
            in.mark(20000);
            System.out.println("publicId: " + publicId + "\nsystemId: "
                    + systemId + "\nURL: " + new_url.toString());
            return new InputSource(in);
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        }*/

        return new InputSource(LabyrinthEntityResolver.class.getResourceAsStream("/data/config/games/LabyrinthLevel.dtd"));
    }

}
