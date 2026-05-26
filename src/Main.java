import lexer.*;
import parser.Parser;
import ast.*;
import interpreter.Interpreter;
import analysis.StaticAnalyzer;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String program = """
                rule r1:
                if temp > 30 then alert

                rule r2:
                if alert then fan_on
                
                rule r3:
                if humidity < 20 then cooling
                """;

        Lexer lexer = new Lexer(program);

        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);

        ProgramNode ast = parser.parseProgram();

        // INTERPRETER

        Map<String, Integer> variables =
                new HashMap<>();

        variables.put("temp", 25);
        variables.put("humidity", 50);

        Set<String> facts =
                new HashSet<>();

        Interpreter interpreter =
                new Interpreter(ast, variables, facts);

        Set<String> result =
                interpreter.execute();

        List<String> ordered =
                new ArrayList<>(result);

        Collections.sort(ordered);

        for (String fact : ordered) {
            System.out.println(fact);
        }

        // STATIC ANALYSIS

        StaticAnalyzer analyzer =
                new StaticAnalyzer(
                        ast,
                        variables,
                        result
                );

        analyzer.analyze();
    }
}