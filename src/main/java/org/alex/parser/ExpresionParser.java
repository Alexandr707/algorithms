package org.alex.parser;

import com.sun.jdi.IntegerType;

import java.util.ArrayList;
import java.util.List;

public class ExpresionParser {

//    expr : plusminus* EOF ;
//
//    plusminus: multdiv ( ( '+' | '-' ) multdiv )* ;
//
//    multdiv : factor ( ( '*' | '/' ) factor )* ;
//
//    factor : NUMBER | '(' expr ')' ;
    public static void main(String[] args){
        String expresion = "2 + 3 + 54 - 46 + 2 * ((21 - 54) / 3) + 5 * (8 / (6 - 4))";
        List<Lexeme> lexemes = lexAnalize(expresion);
        LexemeBuffer buffer = new LexemeBuffer(lexemes);

        System.out.println(expresion + "=" + expr(buffer));
    }

    enum LexemeType{
        LEFT_BREACKET, RIGHT_BREACKET, NUMBER, OP_PLUS, OP_MINUS, OP_MULT, OP_DIV, EOF;
    }
    public static class  Lexeme{
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value){
            this.type = type;
            this.value = value;
        }
        public Lexeme(LexemeType type, Character value){
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return "Lexeme{" + "type=" + type + ", value=\'" + value + "\'";
        }
    }

    public static class LexemeBuffer{
        private int pos;

        private List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes){
            this.lexemes = lexemes;
        }

        public Lexeme next(){
            return lexemes.get(pos++);
        }

        public void back(){
            if (pos > 0) pos--;
        }

        public int getPos(){
            return pos;
        }
    }

    public static List<Lexeme> lexAnalize(String expText){
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;

        while(pos < expText.length()){
            char c = expText.charAt(pos);
            switch (c){
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BREACKET, c));
                    pos ++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BREACKET, c));
                    pos ++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos ++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos ++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MULT, c));
                    pos ++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos ++;
                    continue;
                default:
                    if(c>='0' && c<='9'){
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()) break;
                            c = expText.charAt(pos);
                        }while(c>='0' && c<='9');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else{
                        if (c != ' '){
                            throw new RuntimeException("Unexpected character: " + c);
                        } else pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static int expr(LexemeBuffer lexemes){
        Lexeme lexeme= lexemes.next();
        if (lexeme.type == LexemeType.EOF) return 0;
        else{
            lexemes.back();
            return plusMinus(lexemes);
        }
    }

    public static int plusMinus(LexemeBuffer lexemes){
        int value = multDiv(lexemes);
        while(true){
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type){
                case OP_PLUS:
                    value += multDiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multDiv(lexemes);
                    break;
                case EOF:
                case RIGHT_BREACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value + " at: " + lexemes.getPos());
            }
        }
    }

    public static int multDiv(LexemeBuffer lexemes){
        int value = factor(lexemes);
        while(true){
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type){
                case OP_MULT:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                case EOF:
                case RIGHT_BREACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.value + " at: " + lexemes.getPos());
            }
        }

    }

    public static int factor(LexemeBuffer lexemes){
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type){
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LEFT_BREACKET:
                int value = plusMinus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type == LexemeType.RIGHT_BREACKET)
                    return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value + " at: " + lexemes.getPos());
        }

    }
}
