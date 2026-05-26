import lexer.*;
import parser.Parser;
import ast.*;
import interpreter.Interpreter;
import analysis.StaticAnalyzer;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Cambiar el Caso aquí para probar
        runCase8();
    }

    // CASE 1
    public static void runCase1() {
        String program = """
                rule r1:
                if temp > 30 then alert
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 35);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 2
    public static void runCase2() {
        String program = """
                rule r1:
                if temp > 30 then alert

                rule r2:
                if alert then fan_on
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 35);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 3
    public static void runCase3() {
        String program = """
                rule r1:
                if temp > 30 then alert
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 20);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 4
    public static void runCase4() {
        String program = """
                rule r1:
                if temp > 30 AND humidity < 50 then alert
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 35);
        variables.put("humidity", 40);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 5
    public static void runCase5() {
        // Nota: Asegúrate de completar este String si tu parser
        // requiere tokens específicos para el archivo del Caso 5.
        String program = """
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 35);
        variables.put("humidity", 60);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 6
    public static void runCase6() {
        String program = """
                rule r1:
                if temp > 30 then fan_on

                rule r2:
                if humidity < 50 then fan_on
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 35);
        variables.put("humidity", 40);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 7
    public static void runCase7() {
        String program = """
                rule r1:
                if temp > 30 then alert

                rule r2:
                if temp > 30 then alert
                """;

        Map<String, Integer> variables = new HashMap<>();
        // Se añade temp para mantener la fidelidad con el entorno del Caso 7
        variables.put("temp", 30);

        Set<String> facts = new HashSet<>();
        executeProgram(program, variables, facts);
    }

    // CASE 8
    public static void runCase8() {
        String program = """
                rule r1:
                if temp > 30 then alert

                rule r2:
                if alert then fan_on

                rule r3:
                if humidity < 20 then cooling
                """;

        Map<String, Integer> variables = new HashMap<>();
        variables.put("temp", 25);
        variables.put("humidity", 50);

        Set<String> facts = new HashSet<>();
        executeAnalysisOnly(program, variables, facts);
    }

    public static void executeProgram(
            String program,
            Map<String, Integer> variables,
            Set<String> facts) {

        Lexer lexer = new Lexer(program);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        ProgramNode ast = parser.parseProgram();

        Interpreter interpreter = new Interpreter(ast, variables, facts);
        Set<String> result = interpreter.execute();

        List<String> ordered = new ArrayList<>(result);
        Collections.sort(ordered);

        // Imprimir los hechos ordenados (si está vacío, no imprime nada cumpliendo la pauta)
        for (String fact : ordered) {
            System.out.println(fact);
        }

        // El PDF pide las alertas de análisis inmediatamente después de la ejecución
        StaticAnalyzer analyzer = new StaticAnalyzer(ast, variables, result);
        analyzer.analyze();
    }

    public static void executeAnalysisOnly(
            String program,
            Map<String, Integer> variables,
            Set<String> facts) {

        Lexer lexer = new Lexer(program);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        ProgramNode ast = parser.parseProgram();

        StaticAnalyzer analyzer = new StaticAnalyzer(ast, variables, facts);
        analyzer.analyze();
    }
}