/**
 * Created by Diego on 01/04/2017.
 */
public class Token {

    private TagToken idToken;
    private String lexema;
    private int linha;
    private int pRead;

    public Token(){
    }

    public Token(TagToken idToken, String lexema, int linha, int pRead) {
        this.idToken = idToken;
        this.lexema = lexema;
        this.linha = linha;
        this.pRead = pRead;

    }

    public int getpRead() {
        return pRead;
    }

    public void setpRead(int pRead) {
        this.pRead = pRead;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }


    public TagToken getIdToken() {
        return idToken;
    }

    public void setIdToken(TagToken idToken) {
        this.idToken = idToken;
    }
    @Override
    public String toString() {
        return "Token{" +
                "idToken='" + idToken + '\'' +
                ", lexema='" + lexema + '\'' +
                ", linha=" + linha +
                ", pRead=" + pRead +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (getLinha() != token.getLinha()) return false;
        if (getIdToken() != null ? !getIdToken().equals(token.getIdToken()) : token.getIdToken() != null) return false;
        return getLexema() != null ? getLexema().equals(token.getLexema()) : token.getLexema() == null;
    }

    @Override
    public int hashCode() {
        int result = getIdToken() != null ? getIdToken().hashCode() : 0;
        result = 31 * result + (getLexema() != null ? getLexema().hashCode() : 0);
        result = 31 * result + getLinha();
        return result;
    }
}
