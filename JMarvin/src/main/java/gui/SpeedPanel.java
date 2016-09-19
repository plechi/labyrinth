package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SpeedPanel extends JPanel implements ChangeListener {

    private static final int FPS_MIN = 0;
    private static final int FPS_MAX = 20;
    private static final int FPS_INIT = 5;
    private static final int FPS_MULT = 1000;
    private static final double FPS_TAU = 7.0;
    private int delay;
    private JSlider speed_;
    private JLabel info_;
    private ControlLevel control_level_;

    public SpeedPanel() {
        super();
        delay = FPS_INIT;
        setLayout(new GridLayout(0, 1));
        speed_ = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
        speed_.setMajorTickSpacing(10);
        speed_.setMinorTickSpacing(1);
        speed_.setPaintTicks(true);
        speed_.setPaintLabels(true);
        speed_.setBackground(Color.ORANGE);
        speed_.addChangeListener(this);

        info_ = new JLabel();
        info_.setText("     0 = Singlestep");
        this.setBackground(Color.ORANGE);
        this.setBorder(BorderFactory.createTitledBorder("Geschwindigkeit"));

        add(speed_);
        add(info_);

    }

    public void setControl(ControlLevel control) {
        control_level_ = control;
    }

    public int getDelay() {
        if (speed_.getValue() == 0)
            return 0;
        else if (speed_.getValue() == FPS_MAX)
            return 1;
        else
            return (int) (FPS_MULT * Math.exp(-speed_.getValue()
                    / (FPS_MAX / FPS_TAU)));
    }

    public boolean isSingleStepMode() {
        if (speed_.getValue() == 0)
            return true;
        else
            return false;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int) source.getValue();
            if (fps == 0) {
                delay = 0;
                // if(top.turing_machine_!=null)
                // if(top.turing_machine_.checkStartPoint()==false)
                // top.drawStepButton();
                info_.setText("     > 0 = weiter");
                control_level_.changedSpeed();
            } else {
                if (speed_.getValue() == FPS_MAX)
                    delay = 1;
                else
                    delay = (int) (FPS_MULT * Math.exp(-speed_.getValue()
                            / (FPS_MAX / FPS_TAU)));
                info_.setText("     0 = Singlestep");
                control_level_.changedSpeed();
            }
        }
    }
}
