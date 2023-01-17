package com.example.securitymaster.ds;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank(message = "Code cannot be empty")
    @Pattern(regexp ="[A-Za-z]*",message = ("Code cannot contains illegal characters."))
    private String code;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "[A-Za-z-]*",message = ("Name cannot contains illegal characters."))
    private String name;

    @NotBlank(message = "Country cannot be empty")
    @Pattern(regexp = "[A-Za-z-]*",message = ("Country name cannot contains illegal characters."))
    private String country;

    public Department() {
    }

    public Department(String code, String name, String country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }
}
