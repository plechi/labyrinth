package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import controller.SqlCommunicator;

public class RobotEnviromentSensorPanel extends JPanel {
    private HashMap brick_images_;
    private Image current_image_;
    private int internal_state_;
    public final static int WALL = 1;
    public final static int FREE = 2;
    private UpdateObject parent_panel_;
    private String name_;

    public RobotEnviromentSensorPanel(HashMap brick_images,
            UpdateObject parent_panel) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        internal_state_ = FREE;
        current_image_ = (Image) brick_images_.get("FREE_GROUND");
        this.setToolTipText("Anklicken um den Sensortyp zu ändern.");
        initMouseListener();
    }

    public RobotEnviromentSensorPanel(HashMap brick_images,
            UpdateObject parent_panel, String name) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        name_ = name;
        internal_state_ = FREE;
        current_image_ = (Image) brick_images_.get("FREE_GROUND");
        this.setToolTipText("Anklicken um den Sensortyp zu ändern.");
        initMouseListener();
    }

    public RobotEnviromentSensorPanel(HashMap brick_images, int init_state,
            UpdateObject parent_panel) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        setInternalState(init_state);
        this.setToolTipText("Anklicken um den Sensortyp zu ändern.");
        initMouseListener();
    }

    public RobotEnviromentSensorPanel(HashMap brick_images, int init_state,
            UpdateObject parent_panel, String name) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        name_ = name;
        setInternalState(init_state);
        this.setToolTipText("Anklicken um den Sensortyp zu ändern.");
        initMouseListener();
    }

    public void setInternalState(int new_state) {
        // MUST BE REWRITTEN IF A NEW CONST IS ADDED !!!
        if ((new_state >= WALL) && (new_state <= FREE))
            internal_state_ = new_state;
        else
            System.err.println("State must be >=" + WALL + "and <= " + FREE);
        paintState();
    }

    public int getInternalState() {
        return internal_state_;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(current_image_, 0, 0, this);
    }

    private void paintState() {
        if (internal_state_ == WALL)
            current_image_ = (Image) brick_images_.get("WALL_GROUND");
        else if (internal_state_ == FREE)
            current_image_ = (Image) brick_images_.get("FREE_GROUND");
        RobotEnviromentSensorPanel.this.repaint();
    }

    private void initMouseListener() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String tmp = "";
                if (internal_state_ == FREE) {
                    internal_state_ = WALL;
                    tmp = "Wand";
                } else if (internal_state_ == WALL) {
                    internal_state_ = FREE;
                    tmp = "Weg";
                }

                if (name_ != null) {
                    SqlCommunicator.add_log(name_ + " geändert zu " + tmp, 512);
                } else {
                    SqlCommunicator.add_log("Zustand geändert zu " + tmp, 512);
                }

                paintState();
                parent_panel_.updateParent();
            }
        });
    }
}