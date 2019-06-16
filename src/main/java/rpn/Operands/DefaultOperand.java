package rpn.Operands;
import java.util.*;
public interface DefaultOperand {
    public void evaluate(Stack<String> stack);
    public int counter();
}
