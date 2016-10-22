package connorhenke.com.dnd5000;

public class Calculator {

    public static int CP_PER_SP = 10;
    public static int CP_PER_EP = 50;
    public static int CP_PER_GP = 100;
    public static int CP_PER_PP = 1000;

    private int cp;
    private int sp;
    private int ep;
    private int gp;
    private int pp;
    private int splitCp;
    private int splitSp;
    private int splitEp;
    private int splitGp;
    private int splitPp;
    private int remainder;


    public Calculator(){
        cp = 0;
        sp = 0;
        ep = 0;
        gp = 0;
        pp = 0;
        splitCp = 0;
        splitSp = 0;
        splitEp = 0;
        splitGp = 0;
        splitPp = 0;
        remainder = 0;
    }

    public int getCp() {
        return cp;
    }

    public int getSp() {
        return sp;
    }

    public int getEp() {
        return ep;
    }

    public int getGp() {
        return gp;
    }

    public int getPp() {
        return pp;
    }

    public int getSplitCp() {
        return splitCp;
    }

    public int getSplitSp() {
        return splitSp;
    }

    public int getSplitEp() {
        return splitEp;
    }

    public int getSplitGp() {
        return splitGp;
    }

    public int getSplitPp() {
        return splitPp;
    }

    public int getRemainder() {
        return remainder;
    }

    public void addCp(int cp) {
        this.cp += cp;
    }

    public void addSp(int sp) {
        this.sp += sp;
    }

    public void addEp(int ep) {
        this.ep += ep;
    }

    public void addGp(int gp) {
        this.gp += gp;
    }

    public void addPp(int pp) {
        this.pp += pp;
    }

    public int convertToCp() {
        return cp + (sp * CP_PER_SP) + (ep * CP_PER_EP) + (gp * CP_PER_GP) + (pp * CP_PER_PP);
    }

    public void split(int number) {
        int totalCp = convertToCp();
        remainder = totalCp % number;
        splitCp = totalCp / number;

        splitPp =  splitCp / CP_PER_PP;
        splitCp -= splitPp * CP_PER_PP;

        splitGp = splitCp / CP_PER_GP;
        splitCp -= splitGp * CP_PER_GP;

        splitEp = splitCp / CP_PER_EP;
        splitCp -= splitEp * CP_PER_EP;

        splitSp = splitCp / CP_PER_SP;
        splitCp -= splitSp * CP_PER_SP;
    }

    public static double eval(final String str) throws UnsupportedOperationException {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new UnsupportedOperationException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new UnsupportedOperationException("Unknown function: " + func);
                } else {
                    throw new UnsupportedOperationException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
