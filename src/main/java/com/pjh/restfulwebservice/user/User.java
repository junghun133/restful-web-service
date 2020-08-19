package com.pjh.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password"})
@NoArgsConstructor
@JsonFilter("UserInfo")
public class User {

    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;

    @Past
    private Date joinDate;

    @NotNull
    private String password;
    private String ssn;
}
