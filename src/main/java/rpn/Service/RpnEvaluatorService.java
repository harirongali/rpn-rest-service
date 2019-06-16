package rpn.Service;

import org.springframework.stereotype.Component;
import rpn.Operands.OperandMapper;
import rpn.Operands.OperandNotSupported;
import rpn.Rpn;
import java.util.Stack;

@Component
public class RpnEvaluatorService {
    private Stack<String> operandStack = new Stack<>();

    /*
    Validate for the operands and the expression
    */
    public boolean isValid(String expression) {
       String[] operands = expression.split("\\s+");
       int count = 0;
       try {
           for (String operand: operands) {
               if (isStringInt(operand)) {
                   count++;
               } else {
                   count += OperandMapper.getOperation(operand.trim()).counter();
               }
               if (count < 0) {
                   return false;
               }
           }
       } catch (OperandNotSupported ex) {
           return false;
       }
       if (count == 1) return true;
       return false;
    }

    private boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /*
    Extract and evaluate operands
     */
    public Rpn evaluate(Rpn input) {
        String[] operands = input.getExpression().split("\\s+");
        for (String operand: operands) {
            if (isStringInt(operand)) {
                operandStack.push(operand);
            } else {
                OperandMapper.getOperation(operand).evaluate(operandStack);
            }
        }
        input.setResult(operandStack.pop());
        return input;
    }
}
