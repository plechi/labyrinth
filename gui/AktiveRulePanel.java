package gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.util.HashMap;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import controller.Instruction;
import controller.InstructionList;
import controller.IntStringMap;

public class AktiveRulePanel extends javax.swing.JPanel
{
    private HashMap brick_images_;
    private JLabel message_;
    private static final int CONDITION_HEIGHT = 80;
    private static final int CONDITION_WIDTH = 230;
    private Dimension start_dimension_;
    private RuleTreePanel aktive_rule_tree_panel_;
    private JScrollPane aktive_rule_tree_scrollpane_;

    public AktiveRulePanel(HashMap brick_images, IntStringMap mark_array, IntStringMap state_array)
    {
        setLayout(null);
        this.setBackground(Color.ORANGE);
        brick_images_ = brick_images;
        setBorder(BorderFactory.createTitledBorder("nächste aktive Regel"));
        start_dimension_ = this.getSize();
        aktive_rule_tree_panel_= new RuleTreePanel(brick_images_, mark_array, state_array, new Dimension(230, 110));
        aktive_rule_tree_scrollpane_=new JScrollPane(aktive_rule_tree_panel_);
        aktive_rule_tree_scrollpane_.setBounds(13, 20, 247, 105);
        aktive_rule_tree_scrollpane_.setBorder(null);
        aktive_rule_tree_scrollpane_.setBackground(Color.ORANGE);
        this.add(aktive_rule_tree_scrollpane_);
    }

    public void removeAktiveRule()
    {
        aktive_rule_tree_panel_.deleteAllRules();
    }

     public void setInstructionList(InstructionList instruction_list)
     {
        aktive_rule_tree_panel_.setInstructionList(instruction_list);
     }

     public RuleTreePanel getAktiveRuleTreePanel()
     {
	     return aktive_rule_tree_panel_;
	 }
}
