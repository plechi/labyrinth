/*
 * Created on 24.09.2005
 *
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.*;
import java.net.URL;
import java.util.LinkedList;
import java.awt.GridLayout;
import controller.Instruction;
import controller.InstructionList;
import controller.InstructionTxtParser;
import controller.InstructionTxtWriter;
import controller.SqlCommunicator;
import controller.LevelHandler;

/**
 * Contains the Menue shown in the GUI
 */
public class MenuePanel {

    private JMenuBar menue_;
    private JMenu rule_, labyrinth_, level_of_difficulty_, xpertmode_;
    private JMenuItem add_, help_;
    private JMenuItem new_labyrinth_, save_labyrinth_, load_labyrinth_,
            save_rule_, load_rule_;
    private JMenuItem rule_xpert_, labyrinth_xpert_;
    private ButtonGroup difficulty_;

    private int current_selected_, count_;
    private static LevelHandler level_handler_;
    private URL url_;

    private LabyrinthXpertFrame labyrinth_xpert_frame_;
    private int labyrinth_xpert_count_;
    private int index_of_draw_difficult_menue_ = 0;
    private JRadioButtonMenuItem radio_list_[];
    private LinkedList level_name_list_;
    private ControlSimulation simulation_control_;
    private TUGLabyrinth top_;

    public void setControlSimulation(ControlSimulation simulation_control) {
        simulation_control_ = simulation_control;
        init();
    }

    MenuePanel(URL url, LevelHandler level_handler, TUGLabyrinth top_object_) {
        super();
        top_ = top_object_;
        level_handler_ = level_handler;
        url_ = url;
        current_selected_ = 1;
        labyrinth_xpert_count_ = 0;
    }

    private void init() {
        // BEGIN OF NEW RULE BUTTON *******************************************

        add_ = new JMenuItem("Regel hinzufügen");
        add_.setBackground(new Color(127, 127, 127));
        add_.setForeground(Color.ORANGE);

        add_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulation_control_.addEmptyRule();
            }
        });

        // END OF NEW RULE BUTTON
        // **************************************************

        // BEGIN OF EXPERTEN-MODUS BUTTON
        // ****************************************************

        xpertmode_ = new JMenu("Expertenmodus");
        xpertmode_.setBackground(new Color(127, 127, 127));
        xpertmode_.setForeground(Color.ORANGE);

        rule_xpert_ = new JMenuItem("Regelsatz editieren");
        rule_xpert_.setBackground(new Color(127, 127, 127));
        rule_xpert_.setForeground(Color.ORANGE);
        labyrinth_xpert_ = new JMenuItem("Labyrinth editieren");
        labyrinth_xpert_.setBackground(new Color(127, 127, 127));
        labyrinth_xpert_.setForeground(Color.ORANGE);
        xpertmode_.add(rule_xpert_);
        xpertmode_.add(labyrinth_xpert_);

        rule_xpert_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulation_control_.addRuleXpert();
            }
        });
        labyrinth_xpert_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulation_control_.addLabXpert(current_selected_);
            }
        });

        // END EXPERTEN-MODUS BUTTON
        // ********************************************************

        // BEGIN OF RULE MENUE ***********************************************
        rule_ = new JMenu("Regelsätze");
        rule_.setBackground(new Color(127, 127, 127));
        rule_.setForeground(Color.ORANGE);
        save_rule_ = new JMenuItem("speichern");
        save_rule_.setBackground(new Color(127, 127, 127));
        save_rule_.setForeground(Color.ORANGE);
        load_rule_ = new JMenuItem("laden");
        load_rule_.setBackground(new Color(127, 127, 127));
        load_rule_.setForeground(Color.ORANGE);
        rule_.add(save_rule_);
        rule_.add(load_rule_);

        save_rule_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        load_rule_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });
        // END OF RULE MENUE
        // *****************************************************

        // BEGIN OF LABYRINTH MENUE
        // **********************************************
        labyrinth_ = new JMenu("Labyrinth");
        labyrinth_.setBackground(new Color(127, 127, 127));
        labyrinth_.setForeground(Color.ORANGE);
        new_labyrinth_ = new JMenuItem("neues Labyrinth");
        new_labyrinth_.setBackground(new Color(127, 127, 127));
        new_labyrinth_.setForeground(Color.ORANGE);
        new_labyrinth_
                .setActionCommand("MenuEintrag 'neues Labyrinth' ausgewählt");
        new_labyrinth_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulation_control_.drawNewLabyrinth(current_selected_);
            }
        });

        save_labyrinth_ = new JMenuItem("speichern");
        save_labyrinth_.setBackground(new Color(127, 127, 127));
        save_labyrinth_.setForeground(Color.ORANGE);
        load_labyrinth_ = new JMenuItem("laden");
        load_labyrinth_.setBackground(new Color(127, 127, 127));
        load_labyrinth_.setForeground(Color.ORANGE);
        labyrinth_.add(new_labyrinth_);
        labyrinth_.add(save_labyrinth_);
        labyrinth_.add(load_labyrinth_);
        // END OF LABYRINTH MENUE
        // **************************************************

        // BEGIN OF DIFFICULTY MENUE
        // ***********************************************

        drawDifficultMenue();

        // END OF DIFFICULTY MENUE
        // ***********************************************

        // BEGIN OF HELP BUTTON *********************************************
      /* RUPTHO: for now, no help button
        help_ = new JMenuItem("Hilfe");
        help_.setBackground(new Color(127, 127, 127));
        help_.setForeground(Color.ORANGE);
        help_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulation_control_.printHelp();
                // HelpWindow help_window=new HelpWindow(url_);
                // help_window.showHelpWindow();
            }
        });*/
        // END OF HELP BUTTON ***********************************************

        menue_ = new JMenuBar();
        menue_.setLayout(new GridLayout(0, 5));

        menue_.add(add_);
        menue_.add(xpertmode_);

        // menue_.add(rule_);

        // menue_.add(labyrinth_);

        menue_.add(new_labyrinth_);
        menue_.add(level_of_difficulty_);
        index_of_draw_difficult_menue_ = menue_.getMenuCount();
//        menue_.add(help_);
        menue_.setBackground(new Color(127, 127, 127));
        top_.setJMenuBar(menue_);
    }

    public JMenuBar getMenueBar() {
        return menue_;
    }

    public int getCurrentSelectedLevel() {
        return current_selected_;
    }

    public void decrementXpertCount() {
        labyrinth_xpert_count_--;
    }

    public void incrementXpertCount() {
        labyrinth_xpert_count_++;
    }

    public void refreshList(int number) {
        current_selected_ = number;
        refreshList();
    }

    public void refreshList() {
        menue_.remove(index_of_draw_difficult_menue_ - 1);
        level_of_difficulty_.removeAll();
        drawDifficultMenue();
        menue_.add(level_of_difficulty_, index_of_draw_difficult_menue_ - 1);
        top_.setJMenuBar(menue_);
    }

    private void drawDifficultMenue() {
        level_of_difficulty_ = new JMenu("Schwierigkeitsgrad");
        level_of_difficulty_.setBackground(new Color(127, 127, 127));
        level_of_difficulty_.setForeground(Color.ORANGE);
        difficulty_ = new ButtonGroup();

        if (level_handler_ != null) {
            level_name_list_ = level_handler_.getLevelNameList();
            if (labyrinth_xpert_count_ > 0) {
                radio_list_ = new JRadioButtonMenuItem[level_name_list_.size() + 1];
                int tmp = level_name_list_.size() + 1;
            } else {
                radio_list_ = new JRadioButtonMenuItem[level_name_list_.size()];
                int tmp = level_name_list_.size();
            }
            for (int count = 0; count < level_name_list_.size(); count++) {

                if (current_selected_ == count + 1
                        || (current_selected_ == 0
                                && labyrinth_xpert_count_ == 0 && count == 0)) {
                    radio_list_[count] = new JRadioButtonMenuItem(
                            (String) level_name_list_.get(count), true);
                    current_selected_ = count + 1;
                } else
                    radio_list_[count] = new JRadioButtonMenuItem(
                            (String) level_name_list_.get(count));

                radio_list_[count].setBackground(new Color(127, 127, 127));
                radio_list_[count].setForeground(Color.ORANGE);

                radio_list_[count]
                        .addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(
                                    java.awt.event.ItemEvent event) {
                                if (event.getStateChange() == ItemEvent.SELECTED) {
                                    int max;
                                    if (labyrinth_xpert_count_ > 0)
                                        max = level_name_list_.size() + 1;
                                    else
                                        max = level_name_list_.size();
                                    for (int i = 0; i < max; i++) {
                                        if (((JRadioButtonMenuItem) event
                                                .getSource()) == radio_list_[i]) {
                                            radio_list_[i].setSelected(true);
                                            if (i == level_name_list_.size())
                                                current_selected_ = 0;
                                            else
                                                current_selected_ = i + 1;
                                        } else {
                                            radio_list_[i].setSelected(false);
                                        }
                                    }
                                    simulation_control_
                                            .drawNewLabyrinth(current_selected_);
                                }
                            }
                        });

                difficulty_.add(radio_list_[count]);
                level_of_difficulty_.add(radio_list_[count]);

            }
        }

        if (labyrinth_xpert_count_ > 0) {
            int count = level_name_list_.size();
            // current_selected_=0;
            radio_list_[count] = new JRadioButtonMenuItem("Expertemodus", true);
            radio_list_[count].setBackground(new Color(127, 127, 127));
            radio_list_[count].setForeground(Color.ORANGE);
            radio_list_[count]
                    .addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(
                                java.awt.event.ItemEvent event) {
                            if (event.getStateChange() == ItemEvent.SELECTED) {
                                int max;
                                if (labyrinth_xpert_count_ > 0)
                                    max = level_name_list_.size() + 1;
                                else
                                    max = level_name_list_.size();
                                for (int i = 0; i < max; i++) {
                                    if (((JRadioButtonMenuItem) event
                                            .getSource()) == radio_list_[i]) {
                                        radio_list_[i].setSelected(true);
                                        if (i == level_name_list_.size())
                                            current_selected_ = 0;
                                        else
                                            current_selected_ = i + 1;
                                    } else {
                                        radio_list_[i].setSelected(false);
                                    }
                                }
                                simulation_control_.drawNewLabyrinth(0);
                            }
                        }
                    });

            difficulty_.add(radio_list_[count]);
            level_of_difficulty_.add(radio_list_[count]);
        }
    }

}
