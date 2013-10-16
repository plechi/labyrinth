package gui;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import java.util.HashMap;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Color;

import controller.InstructionList;
import controller.IntStringMap;
import controller.Instruction;

public class RuleTreePanel extends javax.swing.JPanel
{
	private static final int CONDITION_HEIGHT = 80;
	private static final int CONDITION_WIDTH = 230;
	private HashMap brick_images_;
	private IntStringMap mark_array_;
    private IntStringMap state_array_;
	private ArrayList rule_list_;
	private Dimension start_dimension_;
	private ControllerGuiCommunicator communicator_;
	private InstructionList instruction_list_;
			
	public RuleTreePanel(HashMap brick_images, IntStringMap mark_array, IntStringMap state_array) 
    {
        this.setLayout(null);
        this.setBackground(Color.ORANGE);
        brick_images_ = brick_images;
		mark_array_=mark_array;
		state_array_=state_array;
        start_dimension_ = this.getSize();
        rule_list_= new ArrayList();
        instruction_list_= new InstructionList();
    }
    
    public RuleTreePanel(HashMap brick_images, IntStringMap mark_array, IntStringMap state_array, Dimension dimension) 
    {
	    this.setLayout(null);
        this.setBackground(Color.ORANGE);
        brick_images_ = brick_images;
        mark_array_=mark_array;
		state_array_=state_array;
        rule_list_= new ArrayList();
        instruction_list_= new InstructionList();
	    start_dimension_=dimension;	    
		this.setSize(start_dimension_ );
		this.repaint();
	}
		
	public void setCommunicator(ControllerGuiCommunicator communicator)
	{
		communicator_=communicator;
	}
	
    public void drawAllRules() 
    {
        this.removeAll();
        for (int index = 0; index < rule_list_.size(); index++)
        {
            RulePanel tmp = (RulePanel) rule_list_.get(index);
            this.add(tmp);
            tmp.setBounds(0, (rule_list_.size()-index-1) * CONDITION_HEIGHT, CONDITION_WIDTH,
                    CONDITION_HEIGHT);
            tmp.repaint();
        }
        
       adjustSize();
    }
    
    public void deleteAllRules()
	{
		rule_list_.clear();
		instruction_list_.removeAllInstructions();
		this.removeAll();
		adjustSize();
	}
    
	public void deleteRule(int id)
	{
		for (int index = 0; index < rule_list_.size(); index++)
        {
	        RulePanel tmp=(RulePanel)rule_list_.get(index);
	        if(tmp.getID()==id)
	        {
		        rule_list_.remove(index);
		        instruction_list_.removeInstructionAtPos(index);
		        this.remove(tmp);
		        communicator_.ruleDeleted(id);
		    }
        }
        drawAllRules();
	}
	
	public void ruleUpdated(int id, Instruction instruction)
	{
		for (int index = 0; index < instruction_list_.size(); index++)
        {
	        if(instruction_list_.getInstructionAtPos(index).getId()==id)
	        {
		        instruction_list_.setInstruction(index, instruction);
		    }
        }
		communicator_.ruleUpdated(id, instruction);
	}
	
	public void updateRule(int id, Instruction instruction)
	{
	 	for (int index = 0; index < rule_list_.size(); index++)
        {
	        RulePanel tmp=(RulePanel)rule_list_.get(index);
	        if(tmp.getID()==id)
	        {
		        tmp.updateInstruction(instruction);
		        instruction_list_.setInstruction(index, instruction);
		    }
        }
	}
			
    public void addEmptyRule(int id)
    {
	    RulePanel rule=new RulePanel(brick_images_, mark_array_, state_array_, id, this);
	    rule_list_.add(rule);
	    instruction_list_.addInstruction(new Instruction(id));
	    //this.add(rule);
	    //rule.setBounds(0, rule_list_.size() * CONDITION_HEIGHT, CONDITION_WIDTH, CONDITION_HEIGHT);
        //adjustSize();
        
        drawAllRules();
	}
	
	public RulePanel getRule(int id)
	{
		RulePanel ret_rule = new RulePanel(brick_images_, mark_array_, state_array_, id, this);
		for (int index = 0; index < rule_list_.size(); index++)
        {
	        RulePanel tmp=(RulePanel)rule_list_.get(index);
	        if(tmp.getID()==id)
	        {
		      ret_rule = tmp;
		    }
        }
        return ret_rule;
	}
	
		
	public InstructionList getInstructionList()
	{
		return instruction_list_;
	}
	
	public void setInstructionList(InstructionList instruction_list)
	{
		instruction_list_=instruction_list;
		rule_list_.clear();
		for (int index = 0; index < instruction_list_.size(); index++)
        {
	        Instruction dummy = instruction_list_.getInstructionAtPos(index);
       	 	RulePanel rule= new RulePanel(brick_images_, mark_array_, state_array_, dummy, dummy.getId(), this);
       	 	rule_list_.add (rule);
        }	
		drawAllRules();
	}
		
	private void adjustSize()
	{
		if ((rule_list_.size()) * CONDITION_HEIGHT > start_dimension_.getHeight()) 
		{
           this.setSize(new Dimension(CONDITION_WIDTH, (rule_list_.size()) * CONDITION_HEIGHT));
           this.setPreferredSize(new Dimension(CONDITION_WIDTH, (rule_list_.size()) * CONDITION_HEIGHT));
        } 
        else 
        {
           this.setSize(start_dimension_ );
           this.setPreferredSize(start_dimension_);
        }
        this.repaint();
	}
}