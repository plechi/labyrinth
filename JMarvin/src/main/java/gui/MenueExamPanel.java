package gui;

import controller.LevelHandler;
import controller.SqlCommunicator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class MenueExamPanel extends javax.swing.JPanel {
    private static final int DELAY_TIME_ = 12;
    private LevelHandler level_handler_;
    private JButton help_button_, new_rule_button_, next_button_;
    private int current_selected_;
    private ArrayList levels_;
    private Timer timer_;
    private ControlSimulation simulation_control_;
    private MenueExamPanel this_;

    public MenueExamPanel(LevelHandler level_handler) {
        this_ = this;
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
        next_button_ = new JButton();

        this.add(help_button_);
        this.add(new_rule_button_);
        this.add(next_button_);
        levels_ = new ArrayList();
        drawNextButton();
        drawNewRuleButton();
        drawHelpButton();
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

    private void drawNextButton() {
        next_button_.setBackground(new Color(127, 127, 127));
        next_button_.setForeground(Color.ORANGE);
        next_button_.setText("Weiter");
        next_button_.setBounds(290, 5, 350, 28);

        if (level_handler_ != null) {
            // HACK -----drawNextButton darf nur 1 mal aufgerufen werden!!!
            int num_levels = level_handler_.getNumLevels();
            if (num_levels >= 3) {
                levels_.add((Object) 1);
                levels_.add((Object) 2);
                ArrayList tmp = new ArrayList();
                final Random random_generator_ = new Random();
                for (int i = 3; i <= num_levels; i++)
                    tmp.add((Object) i);
                for (int i = 3; i <= num_levels; i++)
                    levels_.add(tmp.remove(random_generator_.nextInt(tmp.size())));
                if (levels_.size() > 0) {

                    current_selected_ = (Integer) levels_.remove(0);
                }
            } else
                System.err.println("More than 3 Levels requested");
            // -----------------------------------------------------------
            if (next_button_.getActionListeners().length == 0) {
                performNextButton(DELAY_TIME_);
            }
        }
    }

    public int getCurrentSelected() {
        return current_selected_;
    }

    public void performNextButton(int delay) {
        if (delay == 0)
            delay = DELAY_TIME_;
        next_button_.setVisible(false);
        timer_ = new Timer();
        timer_.schedule(new TimerTask() {
            public void run() {
                next_button_.setVisible(true);
                if (next_button_.getActionListeners().length == 0) {
                    next_button_
                            .addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(
                                        java.awt.event.ActionEvent evt) {
                                    // SqlCommunicator.add_log("Next button pressed");
                                    if (levels_.size() > 0) {
                                        current_selected_ = (Integer) levels_
                                                .remove(0);
                                        simulation_control_
                                                .drawNewLabyrinthSetTimer(current_selected_);
                                        SqlCommunicator.add_log(
                                                "Weiter Knopf getrückt", 16);
                                    } else {
                                        this_.remove(next_button_);
                                        repaint();
                                        SqlCommunicator.add_log(
                                                "Alle Levels bearbeitet!", 16);
                                        simulation_control_.gameFinished();
                                    }
                                    java.awt.event.ActionListener[] tmp = next_button_
                                            .getActionListeners();
                                    next_button_.removeActionListener(tmp[0]);
                                }
                            });
                }
            }
        }, delay);
    }

    public void nextLevel() {
        if (timer_ != null)
            timer_.cancel();
        performNextButton(5);
    }

}