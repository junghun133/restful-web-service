package com.pjh.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    @Size(min = 4, max = 15, message = "ID는 4글자 이상 15글자 이하여야합니다.")
    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    private Date joinDate;
}
