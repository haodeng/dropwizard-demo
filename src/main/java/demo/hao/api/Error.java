package demo.hao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    private String message;

    public Error(String message) {
        this.message = message;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }
}
