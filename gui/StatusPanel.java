package gui;
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
 * class representing a JPanel for plotting status messages
 */
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class StatusPanel extends JPanel
{
    private JLabel message_first_;
    private JLabel message_second_;

//-----------------------------------------------------------------------------
/**
 * Standard constructor
 * @param state internal state, value have to be greater than -1
 * @param cell_mark value have to be greater than -1
 * @param moving_direction relative direction (value between 1 and 4)
 * @exception IllegalStateException thrown when trying to set a wrong value
 * of state, cell_mark or moving_direction
 */
    public StatusPanel()
    {
        super();
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createTitledBorder("Status"));
        message_first_=new JLabel();
        message_second_=new JLabel();

        add(message_first_);
        add(message_second_);
        message_first_.setText("   Marvin-10 programmieren...");
    }

    public void setNewFirstLine(String text)
    {
       message_first_.setText(text);
       this.repaint();
    }
    public void setNewSecondLine(String text)
    {
       message_second_.setText(text);
       this.repaint();
    }
    public void clearFirstLine()
    {
       message_first_.setText(" ");
       this.repaint();
    }
    public void clearSecondLine()
    {
       message_second_.setText(" ");
       this.repaint();
    }
   public void clear()
    {
	   message_first_.setText(" ");
       message_second_.setText(" ");
       this.repaint();
    }
}
