package cbsc.stage1;

public class SubEquation extends Equation {

    public SubEquation() {
        gengerateEquation('-');
    }

    public SubEquation(String s) {
        int index = s.indexOf("-");
        int length = s.length();
        this.setLeft(Integer.parseInt(s.substring(0, index)));
        this.setRight(Integer.parseInt(s.substring(index + 1, length)));
        this.setOp(s.charAt(index));
        //this.setOp('-');
        this.setResult(calculate());
    }

    @Override
    protected int calculate() {
        return this.getLeft() - this.getRight();
    }

}
