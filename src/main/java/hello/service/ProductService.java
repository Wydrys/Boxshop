package hello.service;

import hello.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private RestTemplate restTemplate;
    private String backedServiceUrl;

    @Autowired
    public ProductService(RestTemplate restTemplate, @Value("${backend.service.url}") String backedServiceUrl) {
        this.restTemplate = restTemplate;
        this.backedServiceUrl = backedServiceUrl;
    }


    public void createProduct(Product product) {
        String createProductUrl = backedServiceUrl + "/product";
        try {
            Product response = restTemplate.postForObject(createProductUrl, product, Product.class);
        } catch (RestClientException e) {
            LOG.error("Some occurred when calling backend service", e);
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        String createProductUrl = backedServiceUrl + "/product";
        try {
            return restTemplate.exchange(createProductUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Product>>() {
                    }).getBody();
        } catch (RestClientException e) {
            LOG.error("Some occurred when calling backend service", e);
            throw new RuntimeException(e);
        }
    }
}
