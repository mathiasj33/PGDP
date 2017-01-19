package pgdp.blatt11;

public class Password {

    private final int nrUpperShould, nrLowerShould, nrSpecialShould, nrNumbersShould, lengthShould;
    private final char[] illegalChars;

    public Password(int nrUpperShould, int nrLowerShould, int nrSpecialShould, int nrNumbersShould, int lengthShould, char[] illegalChars) {
        this.nrUpperShould = nrUpperShould;
        this.nrLowerShould = nrLowerShould;
        this.nrSpecialShould = nrSpecialShould;
        this.nrNumbersShould = nrNumbersShould;
        this.lengthShould = lengthShould;
        this.illegalChars = illegalChars;
    }

    public void checkFormat(String pwd) throws IllegalCharExc, NotEnoughExc, NotLongEnoughExc {
        if(pwd.length() < lengthShould) throw new NotLongEnoughExc(lengthShould, pwd.length());
        
        int nrUpper = 0, nrLower = 0, nrSpecial = 0, nrNumbers = 0;
        for(int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            if(isIllegal(c)) throw new IllegalCharExc(c);
            else if(CharUtils.isUpper(c)) nrUpper++;
            else if(CharUtils.isLower(c)) nrLower++;
            else if(CharUtils.isNumber(c)) nrNumbers++;
            else nrSpecial++;
        }
        
        if(nrUpper < nrUpperShould) throw new NotEnoughUpper(nrUpperShould, nrUpper);
        else if(nrLower < nrLowerShould) throw new NotEnoughLower(nrLowerShould, nrLower);
        else if(nrNumbers < nrNumbersShould) throw new NotEnoughNumber(nrNumbersShould, nrNumbers);
        else if(nrSpecial < nrSpecialShould) throw new NotEnoughSpecial(nrSpecialShould, nrSpecial);
    }
    
    private boolean isIllegal(char c) {
        for(char illegal : illegalChars) {
            if(c == illegal) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Password pwd = new Password(2, 2, 2, 2, 8, new char[]{'\n', '\t', '$', '%'});
        try {
            pwd.checkFormat("AA1%&&aa$");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
