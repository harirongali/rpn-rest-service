package rpn.Operands;

public class OperandNotSupported extends RuntimeException {
    public OperandNotSupported() {
        super(String.format("Not a Valid operation"));
    }
}
