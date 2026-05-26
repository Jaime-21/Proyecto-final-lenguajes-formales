package ast;

public class ComparisonNode extends ConditionNode {

    private String identifier;
    private String operator;
    private int value;

    public ComparisonNode(String identifier,
                          String operator,
                          int value) {

        this.identifier = identifier;
        this.operator = operator;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getOperator() {
        return operator;
    }

    public int getValue() {
        return value;
    }
}