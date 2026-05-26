package ast;

import java.util.List;

public class ProgramNode {

    private List<RuleNode> rules;

    public ProgramNode(List<RuleNode> rules) {
        this.rules = rules;
    }

    public List<RuleNode> getRules() {
        return rules;
    }
}