
package com.restuarantclient.restaurantclient.model;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reviewId",
    "reviewText",
    "rating"
})
public class Review {

    @JsonProperty("reviewId")
    private Long reviewId;
    @JsonProperty("rating")
    private Integer rating;
    @JsonProperty("reviewText")
    private String reviewText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reviewId")
    public Long getReviewId() {
        return reviewId;
    }

    @JsonProperty("reviewId")
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    @JsonProperty("reviewText")
    public String getReviewText() {
        return reviewText;
    }

    @JsonProperty("reviewText")
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @JsonProperty("rating")
    public Integer getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Review(Long reviewId, String reviewText, Integer rating) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Review(Integer rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }
}
