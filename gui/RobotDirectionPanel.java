package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import controller.SqlCommunicator;

public class RobotDirectionPanel extends JPanel {
    private HashMap brick_images_;
    private Image current_image_;
    private int internal_state_;
    public final static int UP = 1;
    public final static int RIGHT = 2;
    public final static int DOWN = 3;
    public final static int LEFT = 4;
    public final static int STATE_COUNT = 4;
    private UpdateObject parent_panel_;
    private String name_;

    public RobotDirectionPanel(HashMap brick_images, UpdateObject parent_panel) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        internal_state_ = UP;
        this.setToolTipText("Anklicken um die Bewegungsrichtung zu ändern.");
        current_image_ = (Image) brick_images_.get("ARROW_UP");
        initMouseListener();
    }

    public RobotDirectionPanel(HashMap brick_images, UpdateObject parent_panel,
            String name) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        internal_state_ = UP;
        name_ = name;
        this.setToolTipText("Anklicken um die Bewegungsrichtung zu ändern.");
        current_image_ = (Image) brick_images_.get("ARROW_UP");
        initMouseListener();
    }

    public RobotDirectionPanel(HashMap brick_images, int init_state,
            UpdateObject parent_panel) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        setInternalState(init_state);
        initMouseListener();
    }

    public RobotDirectionPanel(HashMap brick_images, int init_state,
            UpdateObject parent_panel, String name) {
        super();
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        name_ = name;
        setInternalState(init_state);
        initMouseListener();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(current_image_, 0, 0, this);
    }

    public void setInternalState(int new_state) {
        if ((new_state >= UP) && (new_state <= LEFT))
            internal_state_ = new_state;
        else
            System.err.println("Direction must be >= " + UP + " <= " + LEFT);
        paintState();
    }

    public int getInternalState() {
        return internal_state_;
    }

    private void paintState() {
        switch (internal_state_) {
        case UP:
            current_image_ = (Image) brick_images_.get("ARROW_UP");
            this.setToolTipText("Bewegungsrichtung: gehe geradeaus");
            break;
        case RIGHT:
            current_image_ = (Image) brick_images_.get("ARROW_RIGHT");
            this.setToolTipText("Bewegungsrichtung: gehe nach rechts");
            break;
        case DOWN:
            current_image_ = (Image) brick_images_.get("ARROW_DOWN");
            this.setToolTipText("Bewegungsrichtung: gehe zurück");
            break;
        case LEFT:
            current_image_ = (Image) brick_images_.get("ARROW_LEFT");
            this.setToolTipText("Bewegungsrichtung: gehe nach links");
            break;
        }
        RobotDirectionPanel.this.repaint();
    }

    private void initMouseListener() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                internal_state_ = ((internal_state_++) % STATE_COUNT) + 1;
                String direction = "";
                switch (internal_state_) {
                case 1:
                    direction = "Forward";
                    break;
                case 2:
                    direction = "Right";
                    break;
                case 3:
                    direction = "Backward";
                    break;
                case 4:
                    direction = "Left";
                    break;
                default:
                    direction = "Unknown (error)";
                }

                if (name_ != null) {
                    SqlCommunicator.add_log(
                            name_ + " geändert zu " + direction, 512);
                } else {
                    SqlCommunicator.add_log(
                            "Richtung geändert zu " + direction, 512);
                }
                paintState();
                parent_panel_.updateParent();

            }
        });
    }
}