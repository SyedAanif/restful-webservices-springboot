package com.learnweb.restfulwebservices.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about user.")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty(notes = "Name Should have atleast two characters")
    @Size(min = 2,message = "Name Should have atleast two characters")
    private String name;

    @ApiModelProperty(notes = "BirthDate should be a past date")
    @Past(message = "BirthDate should be a past date")
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Post> posts;
}
