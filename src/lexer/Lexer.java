package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {

            char current = input.charAt(pos);

            // Ignorar espacios
            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            // :
            if (current == ':') {
                tokens.add(new Token(TokenType.COLON, ":"));
                pos++;
                continue;
            }

            // >
            if (current == '>') {
                tokens.add(new Token(TokenType.GT, ">"));
                pos++;
                continue;
            }

            // <
            if (current == '<') {
                tokens.add(new Token(TokenType.LT, "<"));
                pos++;
                continue;
            }

            // =
            if (current == '=') {
                tokens.add(new Token(TokenType.EQ, "="));
                pos++;
                continue;
            }

            // Números
            if (Character.isDigit(current)) {

                StringBuilder number = new StringBuilder();

                while (pos < input.length() &&
                        Character.isDigit(input.charAt(pos))) {

                    number.append(input.charAt(pos));
                    pos++;
                }

                tokens.add(new Token(TokenType.NUMBER, number.toString()));
                continue;
            }

            // Identificadores y keywords
            if (Character.isLetter(current)) {

                StringBuilder word = new StringBuilder();

                while (pos < input.length() &&
                        (Character.isLetterOrDigit(input.charAt(pos))
                                || input.charAt(pos) == '_')) {

                    word.append(input.charAt(pos));
                    pos++;
                }

                String lexeme = word.toString();

                switch (lexeme) {

                    case "rule":
                        tokens.add(new Token(TokenType.RULE, lexeme));
                        break;

                    case "if":
                        tokens.add(new Token(TokenType.IF, lexeme));
                        break;

                    case "then":
                        tokens.add(new Token(TokenType.THEN, lexeme));
                        break;

                    case "AND":
                        tokens.add(new Token(TokenType.AND, lexeme));
                        break;

                    default:
                        tokens.add(new Token(TokenType.ID, lexeme));
                }

                continue;
            }

            throw new RuntimeException("Carácter inválido: " + current);
        }

        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }
}