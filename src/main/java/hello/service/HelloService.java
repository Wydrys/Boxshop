package hello.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import hello.dto.Person;

@Service
public class HelloService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloService.class);

    private RestTemplate restTemplate;
    private String backedServiceUrl;

    @Autowired
    public HelloService(RestTemplate restTemplate, @Value("${backend.service.url}") String backedServiceUrl) {
        this.restTemplate = restTemplate;
        this.backedServiceUrl = backedServiceUrl;
    }

    public Person getPayload(String name) {
        try {
            String url = backedServiceUrl + "/" + name;
            return restTemplate.getForObject(url, Person.class);
        } catch (RestClientException e) {
            LOG.error("Exception occurred during service call.", e);
            throw new RuntimeException(e);
        }
    }
}
