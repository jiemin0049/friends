package com.wiredbrain.friends;

import com.wiredbrain.friends.controller.BillionaireController;
import com.wiredbrain.friends.model.Billionaire;
import com.wiredbrain.friends.service.BillionaireService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BillionaireController.class)
public class StandaloneControllerTests {

    @MockBean
    BillionaireService billionaireService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateReadDelete() throws Exception {
        Billionaire b = new Billionaire("Gordon", "Moore");
        List<Billionaire> billionaires = Arrays.asList(b);

        Mockito.when(billionaireService.findAll()).thenReturn(billionaires);

        mockMvc.perform(get("/billionaire"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].first-name", Matchers.is("Gordon")));

    }
}
