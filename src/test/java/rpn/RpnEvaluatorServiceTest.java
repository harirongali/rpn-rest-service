package rpn;

import org.junit.Test;
import rpn.Service.BadRpnExpressionException;
import rpn.Service.RpnEvaluatorService;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class RpnEvaluatorServiceTest {
    RpnEvaluatorService service = new RpnEvaluatorService();
    @Test
    public void  validRpnExpression() throws Exception {
        Rpn input = new Rpn();
        input.setExpression("3 2 * -- ++");
        assertEquals(true, service.isValid(input.getExpression()));
    }

    @Test
    public void  inValidRpnExpression() throws BadRpnExpressionException {
        Rpn input = new Rpn();
        input.setExpression("3 2 * -- ++ -");
        assertEquals(false, service.isValid(input.getExpression()));
    }

    @Test
    public void  inValidOperandInpnExpression() throws BadRpnExpressionException {
        Rpn input = new Rpn();
        input.setExpression("3 2 * -- &");
        assertEquals(false, service.isValid(input.getExpression()));
    }
}
