package rpn.Operands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperandMapper {
    public static DefaultOperand getOperation(String operand) throws OperandNotSupported {
        if (operand.equals("+")) {
            return new PlusOperand();
        } else if (operand.equals("-")) {
            return new MinusOperand();
        } else if (operand.equals("*")) {
            return new MultipleOperand();
        } else if (operand.equals("/")) {
            return new DivisionOperand();
        } else if (operand.equals("++")) {
            return new IncrementOperand();
        } else if (operand.equals("--")) {
            return new DecrementOperand();
        }
        throw new OperandNotSupported();
    }
}
