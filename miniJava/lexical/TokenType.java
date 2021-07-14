package lexical;

public enum TokenType {
    // SPECIALS
    TKN_UNEXPECTED_EOF,
    TKN_INVALID_TOKEN,
    TKN_END_OF_FILE,

    TKN_INTERFACE,
    TKN_EXTENDS,
    
    // SYMBOLS
    TKN_SEMI_COLON,    // ;
    TKN_COMMA,         // ,

    // KEYWORDS
    TKN_OPEN_CHAVE,    // {
    TKN_CLOSE_CHAVE,   // }
    TKN_OPEN_PAR,      // (
    TKN_CLOSE_PAR,     // )

    // OTHERS
    TKN_ID,            // identifier
    TKN_INT,           // int
    TKN_STRING,        // string
    TKN_BOOLEAN,       // boolean
    TKN_VOID,          // void

};
