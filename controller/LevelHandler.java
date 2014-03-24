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
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.net.URL;


import java.util.LinkedList;

public class LevelHandler {

  private LoadLabyrinth load_labyrinth_;
  private GameXMLContentHandler game_xml_content_handler_;
  private String wall_picture_;
  private String way_picture_;
  private String robot_picture_;
  private String diamond_picture_;
  private String start_picture_;
  private int num_levels_;
  private String file_;
  private URL document_base_;
  private boolean is_not_default_style_;
  private boolean is_error_;
  private Direction initial_direction_;
	private IntStringMap mark_array_;
	private String input_;
	private LinkedList level_name_;
	private LinkedList instruction_file_;

  public LevelHandler()
  {
    document_base_= null;
    is_not_default_style_=false;
	  is_error_=false;
	  initial_direction_=null;
  }

  public void setDocumentBase(URL document_base)
  {
    document_base_=document_base;
  }

	public void setMarkArray(IntStringMap mark_array)
  {
    mark_array_=mark_array;
  }

  public void loadGameXmlFile(String filename)
  {
    try
    {
      SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
      SAXParser saxParser = factory.newSAXParser();
      XMLReader xmlReader = saxParser.getXMLReader();
/*
			try
			{
  			String id = "http://xml.org/sax/features/validation";
				//xmlReader.setFeature("http://xml.org/sax/features/validation", true);
				if (xmlReader.getFeature(id))
				{
    			System.out.println("Parser is validating.");
  			}
				else
				{
    			System.out.println("Parser is not validating.");
  			}
			}
			catch (SAXNotRecognizedException e)
			{
  			System.out.println("Can't tell.");
			}
			catch (SAXNotSupportedException e)
			{
  			System.out.println("Wrong time to ask.");
			}
*/
	    game_xml_content_handler_ = new GameXMLContentHandler();
	    game_xml_content_handler_.setDocumentBase(document_base_);
        xmlReader.setContentHandler(game_xml_content_handler_);
        XMLErrorHandler xml_error_handler = new XMLErrorHandler();
		xmlReader.setErrorHandler(xml_error_handler);
        xmlReader.parse(filename);
		is_error_=xml_error_handler.isXMLError();
		if(is_error_==false)
		{
      		wall_picture_ = game_xml_content_handler_.getWallPicture();
      		way_picture_ = game_xml_content_handler_.getWayPicture();
      		robot_picture_ = game_xml_content_handler_.getRobotPicture();
      		diamond_picture_ = game_xml_content_handler_.getDiamondPicture();
      		start_picture_ = game_xml_content_handler_.getStartPicture();
      		num_levels_ = game_xml_content_handler_.getNumLevels();
			level_name_ = game_xml_content_handler_.getLevelNameList();
		}
    }
    catch(SAXException exc)
    {
      System.err.println(exc.getMessage());
    }
    catch(ParserConfigurationException exc)
    {
      System.err.println(exc.getMessage());
    }
    catch(IOException exc)
    {
      System.err.println(exc.getMessage());
    }
  }

  public int getNumLevels()
  {
		if(is_error_==false)
    	return num_levels_;
		else
			return -1;
  }

/**
* loadLevel is used to fill load_labyrinth_ with a LoadLabyrinth object which can be either a new one or a copy of the last one.
*@param number The level choosed in the LabyrinthChoosePanel
*@param keep_labyrinth Used to determine if the old labyrinth should be kept or a new one should be generated
*@author user
*/
  public void loadLevel(int number)
  {
		if(is_error_==false)
		{
			    file_ = game_xml_content_handler_.getFile(number); //get filename from Game.xml
				if (file_ != null)
				{
				  is_not_default_style_=false;
					load_labyrinth_ = new LoadLabyrinth(file_, document_base_, mark_array_);
					is_not_default_style_=load_labyrinth_.isStyle();
			}
		}
  }

  public void loadLevel(String filename)
  {
		if(is_error_==false)
		{
			is_not_default_style_=false;
			file_ = filename;

			load_labyrinth_ = new LoadLabyrinth(file_, document_base_, mark_array_);
			is_not_default_style_=load_labyrinth_.isStyle();
		}
  }

  public void loadLevelFromString(String input)
  {
		if(is_error_==false)
		{
			is_not_default_style_=false;
			input_ = input;

			load_labyrinth_ = new LoadLabyrinth(input_, document_base_, mark_array_, true);
			is_not_default_style_=load_labyrinth_.isStyle();
		}
  }

	public LinkedList getLevelNameList()
	{
		return level_name_;
	}

  public LabyrinthField getLabyrinthField()
  {
		if(is_error_==false)
    {
      LoadLabyrinth lab = new LoadLabyrinth(load_labyrinth_);
    	return lab.getLabyrinthField();
    }
		else
			return null;
  }

  public IntStringMap getMarkArray()
  {
		return load_labyrinth_.getMarkArray();
  }

  public String getWallPicture()
  {

    if(is_not_default_style_)
    	return load_labyrinth_.getWallPicture();
    else
    	return wall_picture_;
  }

  public String getWayPicture()
  {
    if(is_not_default_style_)
      return load_labyrinth_.getWayPicture();
    else
      return way_picture_;
  }

  public String getRobotPicture()
  {
    if(is_not_default_style_)
      return load_labyrinth_.getRobotPicture();
    else
      return robot_picture_;
  }

  public String getDiamondPicture()
  {
    if(is_not_default_style_)
      return load_labyrinth_.getDiamondPicture();
    else
      return diamond_picture_;
  }

  public String getStartPicture()
  {
    if(is_not_default_style_)
      return load_labyrinth_.getStartPicture();
    else
      return start_picture_;
  }
  public boolean woar()
  {
  	return true;
  }

}
