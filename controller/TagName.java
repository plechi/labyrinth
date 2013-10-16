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
/**
 *class contains the tag names which are used in the labyrinth XML file
 *
 */

public class TagName
{
  //Game XML
  public final static String LABYRINTHGAME = "LabyrinthGame";
  public final static String   DEFAULTLEVEl = "DefaultLevel";
  public final static String     WALLPICTURE = "WallPicture";
  public final static String     WAYPICTURE = "WayPicture";
  public final static String     ROBOTPICTURE = "RobotPicture";
  public final static String     DIAMONDPICTURE = "DiamondPicture";
  public final static String     STARTPICTURE = "StartPicture";
  public final static String   LEVELS = "Levels";
  public final static String     NUMLEVELS = "NumLevels";
  public final static String     LEVEL = "Level";
  public final static String       NUMBER = "Number";
  public final static String       FILE = "File";

  //Instruction XML
  public final static String    LABYRINTHINSTRUCTION = "LabyrinthInstruction";
  public final static String     INSTRUCTIONLIST = "InstructionList";
  public final static String      INSTRUCTION = "Instruction";
  public final static String       CONDITION = "Condition";
  public final static String         CONDSTATE = "CS";
  public final static String         CONDCELLMARK = "CCM";
  public final static String         WALLLEFT = "L";
  public final static String         WALLRIGHT = "R";
  public final static String         WALLAHEAD = "A";
  public final static String       ACTION = "Action";
  public final static String         ACTIONSTATE = "AS";
  public final static String         ACTIONCELLMARK = "ACM";
  public final static String         MOVINGDIRECTION = "MD";

  //LabyrinthLevel XML
  public final static String  LABYRINTHLEVEL = "LabyrinthLevel";
  public final static String    NAME = "Name";
  public final static String    INSTRUCTIONFILE = "InstructionFile";
  public final static String    DIMENSION  = "Dimension";
  public final static String      WIDTH  = "width";
  public final static String      HEIGHT  = "height";
  public final static String    LABYRINTH  = "Labyrinth";
  public final static String      STARTSYMBOL  = "StartSymbol";
  public final static String      DIAMONDSYMBOL  = "DiamondSymbol";
  public final static String      WAYSYMBOL  = "WaySymbol";
  public final static String      ZEROSYMBOL  = "ZeroSymbol";
  public final static String      ONESYMBOL  = "OneSymbol";
  public final static String      TWOSYMBOL  = "TwoSymbol";
  public final static String      THREESYMBOL  = "ThreeSymbol";
	public final static String      SYMBOLLIST  = "SymbolList";
	public final static String      	SYMBOL  = "Symbol";
	public final static String      		CHARSYMBOL  = "CharSymbol";
	public final static String      		STRINGSYMBOL  = "StringSymbol";
  public final static String      STARTDIRECTION  = "StartingDirection";
  public final static String      WALLSYMBOL  = "WallSymbol";
  public final static String      LABYRINTHFIELD  = "LabyrinthField";
  public final static String    CREATE  = "Create";
  public final static String      DIAMONDS  = "Diamonds";
  public final static String      WAYCELLS  = "WayCells";
  public final static String      MINWAYLENGHT  = "MinWayLenght";
  public final static String      MAXWAYLENGHT  = "MaxWayLenght";
  public final static String      BUILDANGLES  = "BuildAngles";
  public final static String      BUILDTREES  = "BuildTrees";
  public final static String      BUILDLOOPS  = "BuildLoops";
  public final static String      BUILDPLACES  = "BuildPlaces";
  public final static String    STYLE  = "Style";
  //public final static String      WALLPICTURE  = "WallPicture";
  //public final static String      WAYPICTURE  = "WayPicture";
  //public final static String      ROBOTPICTURE  = "RobotPicture";
  //public final static String      DIAMONDPICTURE  = "DiamondPicture";
  //public final static String      STARTPICTURE  = "StartPicture";

  //InstructionTXT XML
  public final static String  LABYRINTHINSTRUCTIONTXT = "LabyrinthInstructionTXT";
  public final static String    CONDITIONSTARTSTRING = "ConditionStartString";
  public final static String    CONDITIONENDSTRING  = "ConditionEndString";
  public final static String    ACTIONSTARTSTRING  = "ActionStartString";
  public final static String    ACTIONENDSTRING  = "ActionEndString";
  public final static String    CONDITIONACTIONSEPERATORSTRING  = "ConditionActionSeparatorString";
  public final static String    DEACTIVATEINSTRUCTIONSTRING = "DeactivateInstructionString";
  public final static String    SEPERATORSTRING  = "SeparatorString";
  public final static String	COMMENTOUTLINESTRING="CommentOutLineString";
  public final static String	COMMENTOUTSTARTSTRING="CommentOutStartString";
  public final static String	COMMENTOUTENDSTRING="CommentOutEndString";
  public final static String    WALLSTRING  = "WallString";
  public final static String    WAYSTRING  = "WayString";
  public final static String    DIRECTIONLEFTSTRING  = "DirectionLeftString";
  public final static String    DIRECTIONAHEADSTRING  = "DirectionAheadString";
  public final static String    DIRECTIONRIGHTSTRING  = "DirectionRightString";
  public final static String    DIRECTIONBACKSTRING  = "DirectionBackString";
}


