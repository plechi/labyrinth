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

import java.io.*;

/**
 * WriteInstructionsToXML - Writes given InstructionList to the given XML file
 * 
 * @author Bojan Hrnkas
 * 
 */
public class WriteInstructionsToXML {

    private InstructionList instructions_;

    // -----------------------------------------------------------------------------
    /**
     * standard constructor
     */
    public WriteInstructionsToXML(InstructionList instructions) {
        instructions_ = instructions;
    }

    // -----------------------------------------------------------------------------
    /**
     * write the instruction.dtd file in the folder directory
     * 
     * @param folder
     */
    public void writeDTDFile(String folder) {
        try {
            String file_name = folder + "\\instruction.dtd";
            OutputStream fout = new FileOutputStream(file_name);
            OutputStream bout = new BufferedOutputStream(fout);
            OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");

            out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\r\n\r\n");
            out.write("<!ELEMENT LabyrinthInstruction (WallSymbol, WaySymbol, InstructionList)>\r\n");
            out.write("<!ELEMENT WallSymbol (#PCDATA)>\r\n");
            out.write("<!ELEMENT WaySymbol (#PCDATA)>\r\n");
            out.write("<!ELEMENT InstructionList (Instruction*)>\r\n");
            out.write("<!ELEMENT Instruction (Condition, Action)>\r\n");
            out.write("<!ELEMENT Condition (L, A, R, CS, CCM)>\r\n");
            out.write("<!ELEMENT L (#PCDATA)>\r\n");
            out.write("<!ELEMENT A (#PCDATA)>\r\n");
            out.write("<!ELEMENT R (#PCDATA)>\r\n");
            out.write("<!ELEMENT CS (#PCDATA)>\r\n");
            out.write("<!ELEMENT CCM (#PCDATA)>\r\n");
            out.write("<!ELEMENT Action (AS, ACM, MD)>\r\n");
            out.write("<!ELEMENT AS (#PCDATA)>\r\n");
            out.write("<!ELEMENT ACM (#PCDATA)>\r\n");
            out.write("<!ELEMENT MD (#PCDATA)>");
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            System.err
                    .println("This VM does not support the Latin-1 character set.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------
    /**
     * write the instruction xml file
     * 
     * @param filename
     */
    public void writeXMLFile(String file_name) {
        try {
            OutputStream fout = new FileOutputStream(file_name);
            OutputStream bout = new BufferedOutputStream(fout);
            OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
            out.write("<?xml version=\"1.0\" ");
            out.write("encoding=\"ISO-8859-1\"?>\r\n");
            out.write("<!DOCTYPE LabyrinthInstruction SYSTEM \"Instruction.dtd\">\r\n");
            writeLabInstruction(out);
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            System.err
                    .println("This VM does not support the Latin-1 character set.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    // ------------------------------------------------------------------------------
    /**
     * method transforms a string to an other one which can be used as a start
     * tag
     * 
     * @param tag_name
     *            name of the tag
     * @return transformed string
     */
    protected String toStartTagString(String tag_name) {
        String tag_string = "<" + tag_name + ">";
        return tag_string;
    }

    // ------------------------------------------------------------------------------
    /**
     * method transforms a string to an other one which can be used as a end tag
     * 
     * @param tag_name
     *            name of the tag
     * @return transformed string
     */
    protected String toEndTagString(String tag_name) {
        String tag_string = "</" + tag_name + ">\r\n";
        return tag_string;
    }

    // ------------------------------------------------------------------------------
    /**
     * method transforms a string to an other one which can be used as a end tag
     * 
     * @param out
     * @exception IOException
     *                thrown
     */
    protected void writeLabInstruction(OutputStreamWriter out)
            throws IOException {
        try {
            out.write(toStartTagString(TagName.LABYRINTHINSTRUCTION) + "\r\n");

            writeSymbol(out, TagName.WALLSYMBOL, LabSymbols.WALLSYMBOL);

            writeSymbol(out, TagName.WAYSYMBOL, LabSymbols.WAYSYMBOL);

            writeInstructionList(out);

            out.write(toEndTagString(TagName.LABYRINTHINSTRUCTION) + "\r\n");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

    }

    // ------------------------------------------------------------------------------
    /**
     * method write the instruction list
     * 
     * @param out
     * @exception IOException
     *                thrown
     */
    protected void writeInstructionList(OutputStreamWriter out)
            throws IOException {
        try {
            out.write(toStartTagString(TagName.INSTRUCTIONLIST) + "\r\n");

            for (int inst_count = 0; inst_count < instructions_.size(); inst_count++) {
                Instruction temp_instruction = instructions_
                        .getInstructionAtPos(inst_count);
                writeInstruction(out, temp_instruction);
            }

            out.write(toEndTagString(TagName.INSTRUCTIONLIST) + "\r\n");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * method write the instruction
     * 
     * @param out
     * @param instruction
     * @exception IOException
     *                thrown
     */
    protected void writeInstruction(OutputStreamWriter out,
            Instruction instruction) throws IOException {
        try {
            out.write(toStartTagString(TagName.INSTRUCTION) + "\r\n");

            writeCondition(out, instruction.getCondition());
            writeAction(out, instruction.getAction());

            out.write(toEndTagString(TagName.INSTRUCTION) + "\r\n");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

    }

    // ------------------------------------------------------------------------------
    /**
     * method write the condition
     * 
     * @param out
     * @param condition
     * @exception IOException
     *                thrown
     */
    protected void writeCondition(OutputStreamWriter out, Condition condition)
            throws IOException {
        try {
            out.write(toStartTagString(TagName.CONDITION) + "\r\n");

            // wall left
            if (condition.wall_left_)
                writeSymbol(out, TagName.WALLLEFT, LabSymbols.WALLSYMBOL);
            else
                writeSymbol(out, TagName.WALLLEFT, LabSymbols.WAYSYMBOL);

            // wall ahead
            if (condition.wall_ahead_)
                writeSymbol(out, TagName.WALLAHEAD, LabSymbols.WALLSYMBOL);
            else
                writeSymbol(out, TagName.WALLAHEAD, LabSymbols.WAYSYMBOL);

            // wall right
            if (condition.wall_right_)
                writeSymbol(out, TagName.WALLRIGHT, LabSymbols.WALLSYMBOL);
            else
                writeSymbol(out, TagName.WALLRIGHT, LabSymbols.WAYSYMBOL);

            // cell status
            writeInteger(out, TagName.CONDSTATE, condition.getState());

            // cell mark
            writeInteger(out, TagName.CONDCELLMARK, condition.getCellMark());

            out.write(toEndTagString(TagName.CONDITION) + "\r\n");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * method write the action
     * 
     * @param out
     * @param action
     * @exception IOException
     *                thrown
     */
    protected void writeAction(OutputStreamWriter out, Action action)
            throws IOException {
        try {
            out.write(toStartTagString(TagName.ACTION) + "\r\n");

            // cell status
            writeInteger(out, TagName.ACTIONSTATE, action.GetState());

            // cell mark
            writeInteger(out, TagName.ACTIONCELLMARK, action.getCellMark());

            // moving direction
            writeInteger(out, TagName.MOVINGDIRECTION,
                    action.getMovingDirection());

            out.write(toEndTagString(TagName.ACTION) + "\r\n");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ------------------------------------------------------------------------------
    /**
     * method write the lab symbols
     * 
     * @param out
     * @param tag_name
     * @param symbol
     * @exception IOException
     *                thrown
     */
    protected void writeSymbol(OutputStreamWriter out, String tag_name,
            String symbol) throws IOException {
        out.write(toStartTagString(tag_name) + symbol
                + toEndTagString(tag_name));
    }

    // ------------------------------------------------------------------------------
    /**
     * method write the integers
     * 
     * @param out
     * @param tag_name
     * @param number
     * @exception IOException
     *                thrown
     */
    protected void writeInteger(OutputStreamWriter out, String tag_name,
            int number) throws IOException {
        out.write(toStartTagString(tag_name) + number
                + toEndTagString(tag_name));
    }

}
