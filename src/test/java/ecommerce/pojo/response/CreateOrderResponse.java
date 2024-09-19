package ecommerce.pojo.response;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orders",
        "productOrderId",
        "message"
})
@Generated("jsonschema2pojo")
public class CreateOrderResponse {

    @JsonProperty("orders")
    private List<String> orders;
    @JsonProperty("productOrderId")
    private List<String> productOrderId;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orders")
    public List<String> getOrders() {
        return orders;
    }

    @JsonProperty("orders")
    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    @JsonProperty("productOrderId")
    public List<String> getProductOrderId() {
        return productOrderId;
    }

    @JsonProperty("productOrderId")
    public void setProductOrderId(List<String> productOrderId) {
        this.productOrderId = productOrderId;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


