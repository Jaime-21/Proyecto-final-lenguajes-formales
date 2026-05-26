package ast;

public class FactNode extends ConditionNode {

    private String identifier;

    public FactNode(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}