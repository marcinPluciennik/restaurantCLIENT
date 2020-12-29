package com.restuarantclient.restaurantclient.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dishId",
    "name",
    "price",
    "cartsIds"
})
public class Dish {

    @JsonProperty("dishId")
    private Long dishId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("cartsIds")
    private List<Object> cartsIds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dishId")
    public Long getDishId() {
        return dishId;
    }

    @JsonProperty("dishId")
    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonProperty("cartsIds")
    public List<Object> getCartsIds() {
        return cartsIds;
    }

    @JsonProperty("cartsIds")
    public void setCartsIds(List<Object> cartsIds) {
        this.cartsIds = cartsIds;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Dish(Long dishId, String name, BigDecimal price) {
        this.dishId = dishId;
        this.name = name;
        this.price = price;
    }

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
