package rpn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import rpn.Service.RpnEvaluatorService;
import org.springframework.http.HttpStatus;
import rpn.Service.BadRpnExpressionException;

@RestController
@RequestMapping("/v1/rpn/evaluate")
public class RpnController {

    @Autowired
    RpnEvaluatorService rpnEvaluatorService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> Evaluate(@RequestBody Rpn input) {
        if (!rpnEvaluatorService.isValid(input.getExpression())) {
            throw new BadRpnExpressionException();
        }
        return new ResponseEntity<>(rpnEvaluatorService.evaluate(input), HttpStatus.OK);
    }
}
