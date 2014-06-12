package gui;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.LayoutManager;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import controller.IntStringMap;
import controller.SqlCommunicator;

public class RobotStatePanel extends JPanel {

    private HashMap brick_images_;
    private Image current_image_;
    private int internal_state_;

    private boolean on_cond_panel_;
    public final static int NONE = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;
    public final static int RED = 3;
    public final static int YELLOW = 4;
    public final static int GRAY = 5;

    // must be the count of states (HACK)
    public final static int STATE_COUNT = 5;
    private IntStringMap state_array_;
    private UpdateObject parent_panel_;
    private String name_;

    public RobotStatePanel(HashMap brick_images, IntStringMap state_array,
            boolean on_cond_panel, UpdateObject parent_panel) {
        super();
        state_array_ = state_array;
        brick_images_ = brick_images;
        parent_panel_ = parent_panel;
        internal_state_ = NONE;
        on_cond_panel_ = on_cond_panel;
        current_image_ = (Image) brick_images_.get("COND_NONE");
        on_cond_panel_ = false;
        initMouseListener();
    }

    public RobotStatePanel(HashMap brick_images, IntStringMap state_array,
            boolean on_cond_panel, UpdateObject parent_panel, String name) {
        this(brick_images, state_array, on_cond_panel, parent_panel);
        name_ = name;
    }

    public RobotStatePanel(HashMap brick_images, int init_state,
            IntStringMap state_array, boolean on_cond_panel,
            UpdateObject parent_panel) {
        this(brick_images, state_array, on_cond_panel, parent_panel);
        setInternalState(init_state);
    }

    public RobotStatePanel(HashMap brick_images, int init_state,
            IntStringMap state_array, boolean on_cond_panel,
            UpdateObject parent_panel, String name) {
        this(brick_images, init_state, state_array, on_cond_panel, parent_panel);
        name_ = name;
    }

    public void setInternalState(int new_state) {
        if (new_state >= NONE)
            internal_state_ = new_state;
        else
            System.err.println("State must be >= " + NONE);
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
        String postnote;
        if (on_cond_panel_)
            postnote = "_1";
        else
            postnote = "";
        // System.out.println(postnote + " : " + on_cond_panel_);
        switch (internal_state_) {
        case NONE:
            current_image_ = (Image) brick_images_.get("COND_NONE" + postnote);
            this.setToolTipText("Anklicken um den Zustand zu ändern.");
            break;
        case BLUE:
            current_image_ = (Image) brick_images_.get("COND_BLUE" + postnote);
            this.setToolTipText("Status: "
                    + state_array_.getFromInt(internal_state_ - 1));
            break;
        case GREEN:
            current_image_ = (Image) brick_images_.get("COND_GREEN" + postnote);
            this.setToolTipText("Status: "
                    + state_array_.getFromInt(internal_state_ - 1));
            break;
        case RED:
            current_image_ = (Image) brick_images_.get("COND_RED" + postnote);
            this.setToolTipText("Status: "
                    + state_array_.getFromInt(internal_state_ - 1));
            break;
        case YELLOW:
            current_image_ = (Image) brick_images_
                    .get("COND_YELLOW" + postnote);
            this.setToolTipText("Status: "
                    + state_array_.getFromInt(internal_state_ - 1));
            break;
        default:
            current_image_ = (Image) brick_images_.get("COND_GRAY" + postnote);
            this.setToolTipText("Status: "
                    + state_array_.getFromInt(internal_state_ - 1));
            break;
        }
        RobotStatePanel.this.repaint();
    }

    private void initMouseListener() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (name_ != null) {
                    SqlCommunicator.add_log(name_ + " geändert zu "
                            + state_array_.getFromInt(internal_state_ - 1), 512);
                } else {
                    SqlCommunicator.add_log("Zustand geändert zu "
                            + state_array_.getFromInt(internal_state_ - 1), 512);
                }
                internal_state_ = ((internal_state_ + 1) % STATE_COUNT);
                /*
                 * String placing = "Condition"; if (placing_ == PLACING_ACTION)
                 * placing = "Action"; SqlCommunicator.add_log("Regel " +
                 * Integer.toString(id_) + ": " + placing +
                 * " Zustand geändert zu " +
                 * labyrinth_.state_array_.getFromInt(internal_state_-1));
                 * System.out.println("Interner Zustand: "+internal_state_ );
                 */
                paintState();
                parent_panel_.updateParent();
            }
        });
    }
}
