package gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class StatisticsPanel extends JPanel {
    private JLabel diamond_;
    private JLabel stepp_;
    private JLabel field_;

    private int diamonds_;
    private int fields_;

    public StatisticsPanel() {
        super();
        diamonds_ = 0;
        fields_ = 0;
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(Color.ORANGE);
        this.setBorder(BorderFactory.createTitledBorder("Statistik"));

        diamond_ = new JLabel();
        diamond_.setText("   Diamanten:   0 von " + diamonds_);

        stepp_ = new JLabel();
        stepp_.setText("   Schritte:   0");

        field_ = new JLabel();
        field_.setText("   besuchte Felder:   0 von " + fields_);

        add(stepp_);
        add(field_);
        add(diamond_);

    }

    public void refreshPanel(int stepp_count, int diamond_count, int field_count) {
        diamond_.setText("   Diamanten:   " + diamond_count + " von "
                + diamonds_);
        stepp_.setText("   Schritte:   " + stepp_count);
        field_.setText("   besuchte Felder:   " + field_count + " von "
                + fields_);
    }

    public void resetPanel() {
        diamond_.setText("   Diamanten:   0 von " + diamonds_);
        stepp_.setText("   Schritte:   0");
        field_.setText("   besuchte Felder:   0 von " + fields_);
    }

    public void clearPanel() {
        diamond_.setText("   Diamanten:   0 von 0");
        stepp_.setText("   Schritte:   0");
        field_.setText("   besuchte Felder:   0 von 0");
    }

    public void setFieldcountDiamondcount(int fields, int diamonds) {
        fields_ = fields;
        diamonds_ = diamonds;
    }
}
