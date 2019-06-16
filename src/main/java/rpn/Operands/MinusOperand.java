package rpn.Operands;

import java.util.Stack;

public class MinusOperand implements DefaultOperand {
    @Override
    public void evaluate(Stack<String> stack) {
        Integer rightVal = Integer.valueOf(stack.pop());
        Integer leftVal = Integer.valueOf(stack.pop());
        stack.push(String.valueOf(leftVal - rightVal));
    }

    @Override
    public int counter() {
        return -1;
    }
}
