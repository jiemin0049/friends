package com.wiredbrain.friends;

import com.wiredbrain.friends.controller.BillionaireController;
import com.wiredbrain.friends.model.Billionaire;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

    @Autowired
    BillionaireController billionaireController;

    @Test
    public void testCreateReadDelete() throws ValidationException {

        Billionaire b = new Billionaire("Gordon", "Moore");
        Billionaire result = billionaireController.create(b);

        Iterable<Billionaire> billionaires = billionaireController.read();
        //Assertions.assertThat(billionaires).first().hasFieldOrPropertyWithValue("firstName", "Gordon");
        Assertions.assertThat(billionaires).filteredOn(bi -> bi.getFirstName().equals("Gordon"));


        billionaireController.delete(result.getId());
        Assertions.assertThat(billionaireController.read()).isEmpty();
    }

    @Test(expected = ValidationException.class)
    public void errorHandlingValidationExceptionThrown() throws Exception {
        billionaireController.somethingIsWrong();
    }
}
