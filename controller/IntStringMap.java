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
import java.util.ArrayList;
/**
 * class representing a list mapping ints to strings
 */
public class IntStringMap
{
	private ArrayList arraylist_;

//----------------------------------------------------------------------------
/**
 * Standard constructor
 * Initializes a new Array List
 */
	public IntStringMap()
	{
		arraylist_ = new ArrayList();
	}

//----------------------------------------------------------------------------
/**
 * Return the string from a definite int value
 * @param value
 * @return string
 * @exception IndexOutOfBoundsException thrown when trying to set a wrong value
 */
	public String getFromInt(int value)
    throws IndexOutOfBoundsException
	{
		if(value==-1)
			return "";
    else if (value>=arraylist_.size()||value<-1)
      throw(new IndexOutOfBoundsException());
    else
		  return (String) arraylist_.get(value);
	}

//----------------------------------------------------------------------------
/**
 * Insert a new String to the list
 * @param string
 * @return -1, if the string length was null, else the int representation of the string
 */
	public int setString(String string)
	{
		if(string.length() == 0)
			return -1;

		if(arraylist_.contains(string)==false)
		{
			arraylist_.add(string);
		}
		return arraylist_.indexOf(string);
	}

//----------------------------------------------------------------------------
/**
 * Return the int vakue from a definite string
 * @param string
 * @return -1, if the string was not found, else the int value
 */
	public int getFromString(String string)
	{
		if(string.length() == 0)
			return -1;

		return arraylist_.indexOf(string);
	}

//----------------------------------------------------------------------------
/**
 * Get the size of the list
 * @return size of list
 */
	public int size()
	{
		return arraylist_.size();
	}

//----------------------------------------------------------------------------
/**
 * standard toString method for debugging
 * @return info about the labyrinth field in string-format
 */
	public String toString()
	{
		String string = "IntStringMapping:\n";

		for (int count=0;count<arraylist_.size();count++)
		{
			string=string+"("+count+","+arraylist_.get(count)+")\n";
		}

		return string;
	}
}