package parser;

import lexer.*;
import ast.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    private Token currentToken() {
        return tokens.get(pos);
    }

    private void eat(TokenType expected) {

        if (currentToken().getType() == expected) {
            pos++;
        } else {
            throw new RuntimeException(
                    "Se esperaba " + expected +
                            " pero se encontró " +
                            currentToken().getType()
            );
        }
    }

    // Program -> RuleList
    public ProgramNode parseProgram() {

        List<RuleNode> rules = new ArrayList<>();

        while (currentToken().getType() != TokenType.EOF) {
            rules.add(parseRule());
        }

        return new ProgramNode(rules);
    }

    // Rule -> rule id : if Cond then Action
    private RuleNode parseRule() {

        eat(TokenType.RULE);

        String ruleName = currentToken().getLexeme();
        eat(TokenType.ID);

        eat(TokenType.COLON);

        eat(TokenType.IF);

        ConditionNode condition = parseCondition();

        eat(TokenType.THEN);

        ActionNode action = parseAction();

        return new RuleNode(ruleName, condition, action);
    }

    // Cond -> Atom (AND Atom)*
    private ConditionNode parseCondition() {

        ConditionNode left = parseAtom();

        while (currentToken().getType() == TokenType.AND) {

            eat(TokenType.AND);

            ConditionNode right = parseAtom();

            left = new AndNode(left, right);
        }

        return left;
    }

    // Atom -> id RelOp value | id
    private ConditionNode parseAtom() {

        String identifier = currentToken().getLexeme();

        eat(TokenType.ID);

        TokenType next = currentToken().getType();

        // comparación
        if (next == TokenType.GT ||
                next == TokenType.LT ||
                next == TokenType.EQ) {

            String operator = currentToken().getLexeme();

            eat(next);

            int value = Integer.parseInt(
                    currentToken().getLexeme()
            );

            eat(TokenType.NUMBER);

            return new ComparisonNode(
                    identifier,
                    operator,
                    value
            );
        }

        // fact
        return new FactNode(identifier);
    }

    private ActionNode parseAction() {

        String identifier = currentToken().getLexeme();

        eat(TokenType.ID);

        return new ActionNode(identifier);
    }
}