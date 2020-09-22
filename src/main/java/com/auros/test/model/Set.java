package com.auros.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Set {

    @JsonProperty
    private int id_set;

    @JsonProperty
    private String title;

    @JsonProperty("delete")
    private String deleteFieldWithHml;
}
