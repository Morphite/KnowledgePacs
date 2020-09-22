package com.auros.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pac {

    @JsonProperty
    private int id_pac;

    @JsonProperty
    private String title;

    @JsonProperty("desc")
    private String description;

    @JsonProperty("date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate creation_date;

    @JsonProperty("delete")
    private String deleteFieldWithHtml;

}
