
package pgdp.blatt11;

public class IllegalCharExc extends Exception {
    private char used;

    public IllegalCharExc(char used) {
        this.used = used;
    }
    
    @Override
    public String toString() {
        return "Error: Use of the character " + getStringRepresentation(used) + " is not permitted.";
    }
    
    public String getStringRepresentation(char c) {
        switch(c) {
            case '\n': return "line break (\\n)";
            case '\t': return "tab (\\t)";
            case '\r': return "carriage return (\\r)";
            case '\b': return "backspace (\\b)";
            case '\f': return "formfeed (\\f)";
            default: return "" + c;
        }
    }
}
