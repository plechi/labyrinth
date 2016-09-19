package gui;

import controller.Action;
import controller.IntStringMap;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ActionPanel extends JPanel implements UpdateObject {

    private RobotBottomSensorPanel bottom_sensor_;
    private RobotDirectionPanel direction_panel_;
    private RobotStatePanel state_panel_;
    private HashMap brick_images_;
    private RulePanel parent_panel_;
    private int id_;

    public ActionPanel(HashMap brick_images, IntStringMap mark_array,
            IntStringMap state_array, RulePanel parent_panel, int id) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        id_ = id;
        bottom_sensor_ = new RobotBottomSensorPanel(brick_images_, mark_array,
                this, "Regel " + id_ + " Aktion: Boden Sensor");
        direction_panel_ = new RobotDirectionPanel(brick_images_, this,
                "Regel " + id_ + " Aktion: Bewegungsrichtung");
        state_panel_ = new RobotStatePanel(brick_images_, state_array, false,
                this, "Regel " + id_ + " Aktion: Roboterstatus");
        initComponents();
    }

    public ActionPanel(HashMap brick_images, IntStringMap mark_array,
            IntStringMap state_array, Action action, RulePanel parent_panel,
            int id) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        id_ = id;

        int direction = 0;
        switch (action.getMovingDirection()) {
        case Action.MOVE_AHEAD:
            direction = RobotDirectionPanel.UP;
            break;
        case Action.MOVE_RIGHT:
            direction = RobotDirectionPanel.RIGHT;
            break;
        case Action.MOVE_LEFT:
            direction = RobotDirectionPanel.LEFT;
            break;
        case Action.MOVE_BACK:
            direction = RobotDirectionPanel.DOWN;
        }

        bottom_sensor_ = new RobotBottomSensorPanel(brick_images_,
                action.getCellMark() + 1, mark_array, this, "Regel " + id_
                        + " Aktion: Boden Sensor");

        direction_panel_ = new RobotDirectionPanel(brick_images_, direction,
                this, "Regel " + id_ + " Aktion: Bewegungsrichtung");

        state_panel_ = new RobotStatePanel(brick_images_,
                action.GetState() + 1, state_array, false, this, "Regel " + id_
                        + " Aktion: Bewegungsrichtung");

        initComponents();
    }

    public void updateParent() {
        parent_panel_.actionUpdated();
    }

    private void initComponents() {
        JLabel top_label = new JLabel();
        JLabel info_label = new JLabel();
        this.setLayout(null);
        this.add(top_label);
        this.add(info_label);
        this.add(bottom_sensor_);
        this.add(direction_panel_);
        this.add(state_panel_);
        top_label.setText("Robotersteuerung:");
        top_label.setForeground(Color.white);
        top_label.setBounds(5, 2, 140, 15);
        info_label.setText("Gehe:");
        info_label.setBounds(10, 20, 140, 20);
        info_label.setForeground(Color.white);
        direction_panel_.setBounds(50, 20, 20, 20);
        bottom_sensor_.setBounds(75, 20, 20, 20);
        state_panel_.setBounds(100, 25, 30, 15);
        setBackGround(Color.DARK_GRAY);
        this.setBorder(new javax.swing.border.SoftBevelBorder(
                javax.swing.border.BevelBorder.RAISED));
        repaint();
    }

    public Action getAction() {
        int direction = 0;
        switch (direction_panel_.getInternalState()) {
        case RobotDirectionPanel.UP:
            direction = Action.MOVE_AHEAD;
            break;
        case RobotDirectionPanel.RIGHT:
            direction = Action.MOVE_RIGHT;
            break;
        case RobotDirectionPanel.LEFT:
            direction = Action.MOVE_LEFT;
            break;
        case RobotDirectionPanel.DOWN:
            direction = Action.MOVE_BACK;
        }

        Action ret_action = new Action(state_panel_.getInternalState() - 1,
                bottom_sensor_.getInternalState() - 1, direction);
        return ret_action;
    }

    public void setBackGround(Color action_color) {
        this.setBackground(action_color);
    }

    public void updateAction(Action action) {
        bottom_sensor_.setInternalState(action.getCellMark() + 1);

        int direction = 0;
        switch (action.getMovingDirection()) {
        case Action.MOVE_AHEAD:
            direction = RobotDirectionPanel.UP;
            break;
        case Action.MOVE_RIGHT:
            direction = RobotDirectionPanel.RIGHT;
            break;
        case Action.MOVE_LEFT:
            direction = RobotDirectionPanel.LEFT;
            break;
        case Action.MOVE_BACK:
            direction = RobotDirectionPanel.DOWN;
        }
        direction_panel_.setInternalState(direction);
        state_panel_.setInternalState(action.GetState() + 1);
    }
}