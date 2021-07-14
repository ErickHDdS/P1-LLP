package syntatic;

import java.io.IOException;

//import interpreter.command.Command;
import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.LexicalException;
import lexical.TokenType;

public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;

    public SyntaticAnalysis(LexicalAnalysis lex) {
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public void start() throws LexicalException, IOException {
        procInterface();

        eat(TokenType.TKN_END_OF_FILE);
    }

    private void advance() {
        //System.out.println("Advanced (\"" + current.token + "\", " +
        //    current.type + ")");
        current = lex.nextToken();
    }

    private void eat(TokenType type) {
        //System.out.println("Expected (..., " + type + "), found (\"" + 
        //     current.token + "\", " + current.type + ")");
        if (type == current.type) {
            current = lex.nextToken();
        } else {
            showError();
        }
    }

    private void showError() {
       /*
        System.out.printf("%02d: ", lex.getLine());

        switch (current.type) {
            case TKN_INVALID_TOKEN:
                System.out.printf("Lexema inválido [%s]\n", current.token);
                break;
            case TKN_UNEXPECTED_EOF:
            case TKN_END_OF_FILE:
                System.out.printf("Fim de arquivo inesperado\n");
                break;
            default:
                System.out.printf("Lexema não esperado [%s]\n", current.token);
                break;
        }
        */
        System.out.println("Não");

        System.exit(1);
    }

    //<interface>	:: = interface <id> [extends <extends>] '{' [<method> | <interface> {<method> | <interface>}] '}'
    private void procInterface() throws LexicalException, IOException {
		eat(TokenType.TKN_INTERFACE);
        procId();

        if(current.type == TokenType.TKN_EXTENDS) {
            eat(TokenType.TKN_EXTENDS);
            procExtends();
        }

        eat(TokenType.TKN_OPEN_CHAVE);

        if(current.type == TokenType.TKN_STRING || current.type == TokenType.TKN_INT || current.type == TokenType.TKN_VOID || current.type == TokenType.TKN_BOOLEAN || 
            current.type == TokenType.TKN_INTERFACE) {
            while(current.type == TokenType.TKN_STRING || current.type == TokenType.TKN_INT || current.type == TokenType.TKN_VOID || current.type == TokenType.TKN_BOOLEAN || 
                current.type == TokenType.TKN_INTERFACE) {
                //System.out.println(current.type.toString());
                if(current.type == TokenType.TKN_INTERFACE)
                    procInterface();
                else
                    procMethod();
            }
        }

        eat(TokenType.TKN_CLOSE_CHAVE);
	}

    //<extends>	:: = <id> [{' , ' <id>}]
    private void procExtends() throws LexicalException, IOException {
        procId();

        if(current.type == TokenType.TKN_COMMA) {
            while(current.type == TokenType.TKN_COMMA) {
                advance();
                procId();
            }
        }
    }

    //<method> 	:: = <assignment> '(' [ <parametres> <id> [{ ',' <parametres> <id>}] ')' ;
    private void procMethod() throws LexicalException, IOException {
        if(current.type == TokenType.TKN_STRING || current.type == TokenType.TKN_INT || 
            current.type == TokenType.TKN_VOID || current.type == TokenType.TKN_BOOLEAN) {
            procAssignment();
        }

        eat(TokenType.TKN_OPEN_PAR);
        
        if(current.type == TokenType.TKN_STRING || current.type == TokenType.TKN_INT ||
            current.type == TokenType.TKN_BOOLEAN ) {
            procParametres();
            procId();

            if(current.type == TokenType.TKN_COMMA) {
                while(current.type == TokenType.TKN_COMMA) {
                    advance();
                    procParametres();
                    procId();
                }
            }
        }
        eat(TokenType.TKN_CLOSE_PAR);
        eat(TokenType.TKN_SEMI_COLON);
    }

    //<assignment>	:: = <type> <id> 
    private void procAssignment() throws LexicalException, IOException {
        procType();
        procId();
    }

    //<type>		:: = String | int | void | boolean 
    private void procType() throws LexicalException, IOException {
        if(current.type == TokenType.TKN_STRING) {
            eat(TokenType.TKN_STRING);
        }
        else if(current.type == TokenType.TKN_INT) {
            eat(TokenType.TKN_INT);
        }
        else if(current.type == TokenType.TKN_VOID) {
            eat(TokenType.TKN_VOID);
        }
        else {
            eat(TokenType.TKN_BOOLEAN);
        }
    }

    //<parametres>	:: = String | int | boolean
    private void procParametres() throws LexicalException, IOException {
        if(current.type == TokenType.TKN_STRING) {
            eat(TokenType.TKN_STRING);
        }
        else if(current.type == TokenType.TKN_INT) {
            eat(TokenType.TKN_INT);
        }
        else {
            eat(TokenType.TKN_BOOLEAN);
        }
    }

    // <id>
    private void procId() throws LexicalException, IOException {
        eat(TokenType.TKN_ID);
    }
}
