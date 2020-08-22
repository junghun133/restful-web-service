package com.pjh.restfulwebservice.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
   @Id
   @GeneratedValue
   private Integer id;
   private String description;

   // User : Port -> 1 : ( 0 ~ N) -> parents : child
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY)
   private User user;
}
