package com.wiredbrain.friends;

import com.wiredbrain.friends.model.Billionaire;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// Start application
public class SystemTests {

    @Test
    public void testCreateReadDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/billionaire";

        Billionaire b = new Billionaire("Gordon", "Moore");
        ResponseEntity<Billionaire> entity = restTemplate.postForEntity(url, b, Billionaire.class);

        Billionaire[] billionaires = restTemplate.getForObject(url, Billionaire[].class);
        Assertions.assertThat(billionaires).extracting(Billionaire::getFirstName).containsOnly("Gordon");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url, Billionaire[].class)).isEmpty();
    }

    @Test
    public void testErrorHandlingReturnsBadRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/wrong";

        try {
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }
}
