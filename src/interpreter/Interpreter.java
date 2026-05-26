package interpreter;

import ast.*;

import java.util.*;

public class Interpreter {

    private ProgramNode program;

    private Map<String, Integer> variables;

    private Set<String> activeFacts;

    public Interpreter(ProgramNode program,
                       Map<String, Integer> variables,
                       Set<String> activeFacts) {

        this.program = program;
        this.variables = variables;
        this.activeFacts = activeFacts;
    }

    public Set<String> execute() {

        boolean changed;

        do {

            changed = false;

            Set<String> newFacts = new HashSet<>();

            // evaluar reglas
            for (RuleNode rule : program.getRules()) {

                boolean result =
                        evaluateCondition(rule.getCondition());

                if (result) {

                    String fact =
                            rule.getAction().getIdentifier();

                    // evitar duplicados
                    if (!activeFacts.contains(fact)) {

                        newFacts.add(fact);
                    }
                }
            }

            // agregar nuevos facts
            if (!newFacts.isEmpty()) {

                activeFacts.addAll(newFacts);

                changed = true;
            }

        } while (changed);

        return activeFacts;
    }

    private boolean evaluateCondition(ConditionNode condition) {

        // AND
        if (condition instanceof AndNode andNode) {

            return evaluateCondition(andNode.getLeft()) &&
                    evaluateCondition(andNode.getRight());
        }

        // comparación
        if (condition instanceof ComparisonNode cmp) {

            String id = cmp.getIdentifier();

            int value =
                    variables.getOrDefault(id, 0);

            int target = cmp.getValue();

            String op = cmp.getOperator();

            return switch (op) {

                case ">" -> value > target;

                case "<" -> value < target;

                case "=" -> value == target;

                default -> false;
            };
        }

        // fact
        if (condition instanceof FactNode fact) {

            return activeFacts.contains(
                    fact.getIdentifier()
            );
        }

        return false;
    }
}