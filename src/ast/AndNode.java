package ast;

public class AndNode extends ConditionNode {

    private ConditionNode left;
    private ConditionNode right;

    public AndNode(ConditionNode left,
                   ConditionNode right) {

        this.left = left;
        this.right = right;
    }

    public ConditionNode getLeft() {
        return left;
    }

    public ConditionNode getRight() {
        return right;
    }
}