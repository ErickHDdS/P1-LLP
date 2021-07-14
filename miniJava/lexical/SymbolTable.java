package lexical;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();

        // SYMBOLS
        st.put(";", TokenType.TKN_SEMI_COLON);
        st.put(",", TokenType.TKN_COMMA);

        // KEYWORDS
        st.put("{", TokenType.TKN_OPEN_CHAVE);
        st.put("}", TokenType.TKN_CLOSE_CHAVE);
        st.put("(", TokenType.TKN_OPEN_PAR);
        st.put(")", TokenType.TKN_CLOSE_PAR);

        st.put("interface", TokenType.TKN_INTERFACE);
        st.put("extends", TokenType.TKN_EXTENDS);

        st.put("String", TokenType.TKN_STRING);
        st.put("int", TokenType.TKN_INT);
        st.put("void", TokenType.TKN_VOID);
        st.put("boolean", TokenType.TKN_BOOLEAN);
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ?
            st.get(token) : TokenType.TKN_ID;
    }
}
