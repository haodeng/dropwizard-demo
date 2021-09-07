package demo.hao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Name {
    @NotBlank
    private String name;

    @JsonProperty
    public String getName() {
        return name;
    }
}
