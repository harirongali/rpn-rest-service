/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rpn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RpnControllerTest {

    @Autowired
    private MockMvc mockMvc;
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void  evaluateRpnExpression() throws Exception {
        Rpn input = new Rpn();
        input.setExpression("3 2 * -- ++");
        this.mockMvc.perform(post("/v1/rpn/evaluate").contentType("application/json").content(asJsonString(input))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("6"));
    }

    @Test
    public void  evaluateRpnExpressionWithMoreOperands() throws Exception {
        Rpn input = new Rpn();
        input.setExpression("15 7 1 1 + - / 3 * 2 1 1 + + -");
        this.mockMvc.perform(post("/v1/rpn/evaluate").contentType("application/json").content(asJsonString(input))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("5"));
    }

    @Test(expected = NestedServletException.class)
    public void  validateFail() throws Exception {
        Rpn input = new Rpn();
        input.setExpression("15 7 1 1 + - / 3 * 2 1 1 + + - -");
        this.mockMvc.perform(post("/v1/rpn/evaluate").contentType("application/json").content(asJsonString(input))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("5"));
    }

}
