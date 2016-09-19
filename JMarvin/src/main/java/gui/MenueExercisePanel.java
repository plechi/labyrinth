package gui;

import controller.LevelHandler;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

class MenueExercisePanel extends javax.swing.JPanel {
    ControlSimulation simulation_control_;
    private LevelHandler level_handler_;
    private JButton help_button_, new_rule_button_;
    private int current_selected_;

    public MenueExercisePanel(LevelHandler level_handler) {
        level_handler_ = level_handler;
        current_selected_ = 1;
    }

    public void setControlSimulation(ControlSimulation simulation_control) {
        simulation_control_ = simulation_control;
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(Color.ORANGE);
        setBorder(null);
        help_button_ = new JButton();
        new_rule_button_ = new JButton();
        this.add(help_button_);
        this.add(new_rule_button_);
        drawNewRuleButton();
        drawHelpButton();
        drawLevelOfDifficulty();
    }

    private void drawHelpButton() {
        // BEGIN OF HELP BUTTON ***********************************************
        help_button_.setBounds(682, 5, 255, 28);
        help_button_.setBackground(new Color(127, 127, 127));
        help_button_.setForeground(Color.ORANGE);
        help_button_.setText("Hilfe");
        if (help_button_.getActionListeners().length == 0) {
            help_button_.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    simulation_control_.printHelp();
                    // SqlCommunicator.add_log("Help button pressed");
                    // HelpWindow help_window=new HelpWindow(getCodeBase());
                }
            });
        }
        // END OF HELP BUTTON **************************************************
    }

    private void drawNewRuleButton() {
        // BEGIN OF NEW RULE BUTTON *******************************************
        new_rule_button_.setBounds(5, 5, 248, 28);
        new_rule_button_.setBackground(new Color(127, 127, 127));
        new_rule_button_.setForeground(Color.ORANGE);
        new_rule_button_.setText("Regel hinzufügen");
        if (new_rule_button_.getActionListeners().length == 0) {
            new_rule_button_
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(
                                java.awt.event.ActionEvent evt) { // Load rules
                                                                  // button
                                                                  // pressed
                            simulation_control_.addEmptyRule();
                        }
                    });
        }
        // END OF NEW RULE BUTTON
        // **************************************************
    }

    private void drawLevelOfDifficulty() {
        // Create the combo box, select the item at index 4.
        JLabel info_ = new JLabel();
        info_.setOpaque(true);
        info_.setBackground(new Color(127, 127, 127));
        info_.setForeground(Color.ORANGE);
        info_.setText("        Schwierigkeitsgrad   ");
        info_.setBounds(290, 5, 350, 28);
        if (level_handler_ != null) {
            final LinkedList level_name_list_ = level_handler_
                    .getLevelNameList();
            String[] level_list_ = new String[level_name_list_.size()];
            int tmp = 1;
            for (int count = 1; count < level_name_list_.size() + 1; count++) {
                level_list_[count - 1] = (String) level_name_list_
                        .get(count - 1);
                if (current_selected_ == count) {
                    tmp = count - 1;
                }
            }
            final JComboBox level_of_difficulty_ = new JComboBox(level_list_);
            level_of_difficulty_.setBounds(465, 8, 155, 20);
            this.add(level_of_difficulty_);
            this.add(info_);
            level_of_difficulty_.setSelectedIndex(tmp);
            if (level_of_difficulty_.getActionListeners().length == 0) {
                level_of_difficulty_
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent evt) { // Load
                                                                      // rules
                                                                      // button
                                                                      // pressed
                                JComboBox cb = (JComboBox) evt.getSource();
                                String name = (String) cb.getSelectedItem();
                                current_selected_ = cb.getSelectedIndex() + 1;
                                simulation_control_
                                        .drawNewLabyrinth(current_selected_);
                            }
                        });
            }
        }
    }

}