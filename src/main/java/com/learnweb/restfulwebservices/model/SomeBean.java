package com.learnweb.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter(value ="SomeBeanFilter" )
public class SomeBean {

    private String field1;
    private String field2;
    private String field3;
}
