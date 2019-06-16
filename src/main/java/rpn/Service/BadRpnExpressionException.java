package rpn.Service;

public class BadRpnExpressionException extends RuntimeException {
    public BadRpnExpressionException() {
        super(String.format("InValid RPN Expression"));
    }
}
