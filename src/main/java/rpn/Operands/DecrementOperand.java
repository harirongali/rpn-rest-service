package rpn.Operands;

import java.util.Stack;

public class DecrementOperand implements DefaultOperand {
    @Override
    public void evaluate(Stack<String> stack) {
        Integer val = Integer.valueOf(stack.pop());
        stack.push(String.valueOf(val-1));
    }

    @Override
    public int counter() {
        return 0;
    }
}
