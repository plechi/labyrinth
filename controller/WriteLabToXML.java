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
 * class provides methods for generating a labyrinth XML file
 * for the tag names and the labyrinth symbols the classes
 * TagName and LabSymbols are used
 */

public class WriteLabToXML
{
  /** labyrinth field containing the labyrinth data */
  private LabyrinthField lab_field_;

  /** robot containing the robot data */
  //private Robot robot_;

//-----------------------------------------------------------------------------
/**
 * Standart Constructor
 * @param labyrinth_field
 */   
  public WriteLabToXML(LabyrinthField lab_field)
  {
    lab_field_ = lab_field;
    //robot_ = robot;
  }

//------------------------------------------------------------------------------
/**
 * method transforms a string to an other one which can be used as a start tag
 * @param tag_name name of the tag
 * @return transformed string
 */
  protected String toStartTagString(String tag_name)
  {
    String tag_string = "<" + tag_name + ">";
    return tag_string;
  }

//------------------------------------------------------------------------------
/**
 * method transforms a string to an other one which can be used as a end tag
 * @param tag_name name of the tag
 * @return transformed string
 */
  protected String toEndTagString(String tag_name)
  {
    String tag_string = "</" + tag_name + ">\r\n";
    return tag_string;
  }

//------------------------------------------------------------------------------
/**
 * method writes the widht-element to the specified stream
 * @param out stream to use for writing
 * @exception IOException if out.write throws an Exception
 */
  protected void writeWidth(OutputStreamWriter out)
    throws IOException
  {
    out.write(toStartTagString( TagName.WIDTH) + lab_field_.getNumCols() +
    toEndTagString(TagName.WIDTH));
  }

//------------------------------------------------------------------------------
/**
 * method writes the height-element to the specified stream
 * @param out stream to use for writing
 * @exception IOException if out.write throws an Exception
 */
  protected void writeHeight(OutputStreamWriter out)
    throws IOException
  {
    out.write(toStartTagString(TagName.HEIGHT) + lab_field_.getNumRows()+
    toEndTagString(TagName.HEIGHT));
  }

//------------------------------------------------------------------------------
/**
 * method writes the dimension-element and all elements it contains to the specified stream
 * @param out stream to use for writing
 * @exception IOException if out.write throws an Exception
 */
  protected void writeDimension(OutputStreamWriter out)
    throws IOException
  {
    out.write(toStartTagString(TagName.DIMENSION) + "\r\n");
    writeWidth(out);
    writeHeight(out);
    out.write(toEndTagString(TagName.DIMENSION));
  }

  protected void writeName(OutputStreamWriter out)
  throws IOException
{
  out.write(toStartTagString(TagName.NAME) + "Custom Level" +
  toEndTagString(TagName.NAME));
}
  
//------------------------------------------------------------------------------
/**
 * method writes a symbol-element to the specified stream
 * @param out stream to use for writing
 * @param tag_name name of the symbol-element
 * @param symbol symbol to write
 * @exception IOException if out.write throws an Exception
 */
  protected void writeSymbol(OutputStreamWriter out, String tag_name, String symbol)
    throws IOException
  {
    out.write(toStartTagString(tag_name)+ symbol + toEndTagString(tag_name));
  }

//------------------------------------------------------------------------------
/**
 * method writes the startdirection-element to the specified stream
 * @param out stream to use for writing
 * @exception IOException if out.write throws an Exception
 */
  protected void writeStartDirection(OutputStreamWriter out)
    throws IOException
  {
    out.write(toStartTagString(TagName.STARTDIRECTION)+ lab_field_.getStartDirection() +
      toEndTagString(TagName.STARTDIRECTION));
  }

//------------------------------------------------------------------------------
/**
 * method writes the labyrinth-element with the ASCII representation
 * of the labyrinth field to the specified stream
 * @param out stream to use for writing
 * @exception IOException if out.write throws an Exception
 */
  protected void writeLabyrinthField(OutputStreamWriter out)
    throws IOException
  {
    try
    {
      out.write(toStartTagString(TagName.LABYRINTHFIELD) + "\r\n");
      for(int row_count=0 ; row_count< lab_field_.getNumRows(); row_count++)
      {
        for(int col_count = 0; col_count < lab_field_.getNumCols(); col_count++)
        {
          //if(((Cell)lab_field_.getElement(row_count, col_count)).isWall())
          if(lab_field_.isWall(row_count, col_count))
          {
            out.write(LabSymbols.WALLSYMBOL);
          }
          //else if(((Cell)lab_field_.getElement(row_count, col_count)).containsDiamond())
          else if(lab_field_.containsDiamond(row_count, col_count))
          {
            out.write(LabSymbols.DIAMONDSYMBOL);
          }
          //else if(((Cell)lab_field_.getElement(row_count, col_count)).isStartpoint())
          else if(lab_field_.isStartpoint(row_count, col_count))
          {
            out.write(LabSymbols.STARTSYMBOL);
          }
          //else if(((Cell)lab_field_.getElement(row_count, col_count)).isWay())
          else if(lab_field_.isWay(row_count, col_count))
          {
            out.write(LabSymbols.WAYSYMBOL);
          }
        }
        out.write("\r\n");
      }
      out.write(toEndTagString(TagName.LABYRINTHFIELD));
    }
    catch(IndexOutOfBoundsException ex)
    {
      System.out.println(ex.getMessage());
    }

  
  }
  
//-----------------------------------------------------------------------------
/**
 * write the labyrinth xml file
 * @param out
 * @exception IOException thrown
 */ 
  protected void writeLabyrinth(OutputStreamWriter out)
    throws IOException
  {
      try
      {
          out.write(toStartTagString(TagName.LABYRINTH) + "\r\n");
	      writeSymbol(out, TagName.STARTSYMBOL, LabSymbols.STARTSYMBOL);
	      writeSymbol(out, TagName.WALLSYMBOL, LabSymbols.WALLSYMBOL);
	      writeSymbol(out, TagName.DIAMONDSYMBOL, LabSymbols.DIAMONDSYMBOL);
	      writeSymbol(out, TagName.WAYSYMBOL, LabSymbols.WAYSYMBOL);
	      writeSymbol(out, TagName.ZEROSYMBOL, "0");
	      writeSymbol(out, TagName.ONESYMBOL, "1");
	      writeSymbol(out, TagName.TWOSYMBOL, "2");
	      writeSymbol(out, TagName.THREESYMBOL, "3");
	      writeStartDirection(out);
	      writeLabyrinthField(out);
	      out.write(toEndTagString(TagName.LABYRINTH));
      }
      catch(IndexOutOfBoundsException ex)
      {
          System.out.println(ex.getMessage());
      }
  }
  
//------------------------------------------------------------------------------
/**
 * method writes the robotlabyrinth-element and all elements it contains to the specified stream
 * @param out stream to use for writing
 * @exception IOException if out.write throws an Exception
 */
  public void writeRobotLabyrinth(OutputStreamWriter out)
    throws IOException
  {
    out.write(toStartTagString(TagName.LABYRINTHLEVEL) + "\r\n");
    writeName(out);
    writeDimension(out);
    writeLabyrinth(out);
    out.write(toEndTagString(TagName.LABYRINTHLEVEL) + "\r\n");
  }

//------------------------------------------------------------------------------
/**
 * method creates a XML file with the given filename and writes the header and
 * the whole labyrinth data in this file
 * @param file_name name of the XML file
 */
  public void writeXMLFile(String file_name)
  {
    try
    {
      OutputStream fout= new FileOutputStream(file_name);
      OutputStream bout= new BufferedOutputStream(fout);
      OutputStreamWriter out
       = new OutputStreamWriter(bout, "8859_1");
      out.write("<?xml version=\"1.0\" ");
      out.write("encoding=\"ISO-8859-1\"?>\r\n");
      out.write("<!DOCTYPE LabyrinthLevel SYSTEM \"LabyrinthLevel.dtd\">\r\n");
      writeRobotLabyrinth(out);
      out.flush();
      out.close();
    }
    catch(UnsupportedEncodingException e)
    {
      System.err.println(
        "This VM does not support the Latin-1 character set."
        );
    }
    catch (IOException e)
    {
      System.err.println(e.getMessage());
    }

  }
  
//-----------------------------------------------------------------------------
/**
 * write the instruction.dtd file in the folder directory
 * @param folder
 */   
  public void writeDTDFile(String folder)
  {
      try
      {
          String file_name = folder + "\\LabyrinthLevel.dtd";
          OutputStream fout= new FileOutputStream(file_name);
          OutputStream bout= new BufferedOutputStream(fout);
          OutputStreamWriter out
          	= new OutputStreamWriter(bout, "8859_1");
          
          
          out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\r\n\r\n");

          out.write("<!ELEMENT LabyrinthLevel (Name, InstructionFile?, Dimension, Labyrinth?, Create?, Style?)>\r\n");
          out.write("<!ELEMENT Name (#PCDATA)>\r\n");
          out.write("<!ELEMENT InstructionFile (#PCDATA)>\r\n");
          out.write("<!ELEMENT Dimension (width, height)>\r\n");
          out.write("<!ELEMENT width (#PCDATA)>\r\n");
          out.write("<!ELEMENT height (#PCDATA)>\r\n");
          out.write("<!ELEMENT Labyrinth (StartSymbol, WallSymbol, DiamondSymbol, WaySymbol, ZeroSymbol, OneSymbol, TwoSymbol, ThreeSymbol, StartingDirection, LabyrinthField)>\r\n");
          out.write("<!ELEMENT StartSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT WallSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT DiamondSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT WaySymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT ZeroSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT OneSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT TwoSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT ThreeSymbol (#PCDATA)>\r\n");
          out.write("<!ELEMENT StartingDirection (#PCDATA)>\r\n");
          out.write("<!ELEMENT LabyrinthField (#PCDATA)>\r\n");
          out.write("<!ELEMENT Create (Diamonds, WayCells, MinWayLenght, MaxWayLenght, BuildAngles, BuildTrees, BuildLoops, BuildPlaces)>\r\n");
          out.write("<!ELEMENT Diamonds (#PCDATA)>\r\n");
          out.write("<!ELEMENT WayCells (#PCDATA)>\r\n");
          out.write("<!ELEMENT MinWayLenght (#PCDATA)>\r\n");
          out.write("<!ELEMENT MaxWayLenght (#PCDATA)>\r\n");
          out.write("<!ELEMENT BuildAngles (#PCDATA)>\r\n");
          out.write("<!ELEMENT BuildTrees (#PCDATA)>\r\n");
          out.write("<!ELEMENT BuildLoops (#PCDATA)>\r\n");
          out.write("<!ELEMENT BuildPlaces (#PCDATA)>\r\n");
          out.write("<!ELEMENT Style (WallPicture, WayPicture, RobotPicture, DiamondPicture, StartPicture)>\r\n");
          out.write("<!ELEMENT WallPicture (#PCDATA)>\r\n");
          out.write("<!ELEMENT WayPicture (#PCDATA)>\r\n");
          out.write("<!ELEMENT RobotPicture (#PCDATA)>\r\n");
          out.write("<!ELEMENT DiamondPicture (#PCDATA)>\r\n");
          out.write("<!ELEMENT StartPicture (#PCDATA)>\r\n");
          out.flush();
          out.close();
      }
      catch(UnsupportedEncodingException e)
      {
        System.err.println(
          "This VM does not support the Latin-1 character set."
          );
      }
      catch (IOException e)
      {
        System.err.println(e.getMessage());
      }
  }
}
