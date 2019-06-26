package com.wiredbrain.friends;

import com.wiredbrain.friends.model.Billionaire;
import com.wiredbrain.friends.service.BillionaireService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

    @Autowired
    BillionaireService billionaireService;

    @Test
    public void testCreateReadDelete() {
        Billionaire b = new Billionaire("Gordon", "Moore");
        billionaireService.save(b);

        Iterable<Billionaire> billionaires = billionaireService.findAll();
        //Assertions.assertThat(billionaires).first().hasFieldOrPropertyWithValue("firstName", "Gordon");
        Assertions.assertThat(billionaires).filteredOn(bi -> bi.getFirstName().equals("Gordon"));


        billionaireService.deleteAll();
        Assertions.assertThat(billionaireService.findAll()).isEmpty();
    }
}
