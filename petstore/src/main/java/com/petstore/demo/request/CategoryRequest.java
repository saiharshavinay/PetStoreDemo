package com.petstore.demo.request;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest implements Serializable {
    private long id;
    private String name;

}
