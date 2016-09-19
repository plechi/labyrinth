package gui;

import controller.Instruction;
import controller.InstructionList;
import controller.IntStringMap;
import controller.SqlCommunicator;

import java.net.URL;
import java.util.HashMap;

public class ControllerGuiCommunicator {
    private ControlLevel control_level_;
    private ControlSimulation control_simulation_;
    private int instruction_id_;
    private RuleTreePanel rule_tree_panel_, aktive_rule_tree_panel_;
    private HashMap brick_images_;
    private IntStringMap mark_array_;
    private IntStringMap state_array_;
    private LabyrinthXpertFrame labyrinth_xpert_frame_;
    private URL url_;

    public ControllerGuiCommunicator(RuleTreePanel rule_tree_panel,
            AktiveRulePanel aktive_rule_panel, HashMap brick_images,
            IntStringMap mark_array, IntStringMap state_array, URL url) {
        instruction_id_ = 0;
        url_ = url;
        mark_array_ = mark_array;
        state_array_ = state_array;
        rule_tree_panel_ = rule_tree_panel;
        rule_tree_panel_.setCommunicator(this);
        aktive_rule_tree_panel_ = aktive_rule_panel.getAktiveRuleTreePanel();
        aktive_rule_tree_panel_.setCommunicator(this);
        brick_images_ = brick_images;
    }

    public void setCommunicationElements(ControlLevel control_level,
            ControlSimulation control_simulation) {
        control_level_ = control_level;
        control_simulation_ = control_simulation;
    }

    public void newRuleXpert() {
        SqlCommunicator.add_log("Regel Xpert Frame geöffnet", 16);
        InstructionXpertFrame instruction_xpert_frame = new InstructionXpertFrame(
                url_, mark_array_, state_array_,
                getInstructionListFromRuleTree(), this);
    }

    public void xpertRulesUpdated(InstructionList instructions) {
        SqlCommunicator.add_log("Regelsatz vom Xpert Frame neu eingelesen", 32);
        InstructionList tmp = new InstructionList();
        String current_program = "Programm vom Xpert Frame:\n";
        instruction_id_ = 0;
        for (int i = 1; i <= instructions.size(); i++) {
            Instruction dummy = instructions.getInstructionAtPos(instructions
                    .size() - i);
            if (dummy.getId() == 0) {
                instruction_id_++;
                dummy.updateId(instruction_id_);
            }
            tmp.add(dummy);
            current_program += "                          " + dummy.toString();
        }
        SqlCommunicator.add_log(current_program, 128);
        setInstructionListToRuleTree(tmp);
    }

    public void xpertRulesClosed() {
        SqlCommunicator.add_log("Regel Xpert Frame geschlossen", 16);
    }

    public void addEmptyRule() {
        instruction_id_++;
        SqlCommunicator.add_log(
                "Regel hinzugefuegt. Regel id: "
                        + Integer.toString(instruction_id_), 265);
        rule_tree_panel_.addEmptyRule(instruction_id_);
    }

    public void ruleDeleted(int id) {
        rule_tree_panel_.deleteRule(id);
        aktive_rule_tree_panel_.deleteRule(id);
    }

    public void deleteAllRules() {
        rule_tree_panel_.deleteAllRules();
        aktive_rule_tree_panel_.deleteAllRules();
        instruction_id_ = 0;
    }

    public void ruleUpdated(int id, Instruction instruction) {
        rule_tree_panel_.updateRule(id, instruction);
        aktive_rule_tree_panel_.updateRule(id, instruction);
    }

    public InstructionList getInstructionListFromRuleTree() {
        return rule_tree_panel_.getInstructionList();
    }

    public void setInstructionListToRuleTree(InstructionList instructions) {
        rule_tree_panel_.setInstructionList(instructions);
    }

    public void newActiveInstructionList(InstructionList instructions) {
        aktive_rule_tree_panel_.setInstructionList(instructions);
    }

}