package com.petstore.demo.request;

import com.petstore.demo.model.Category;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest implements Serializable {
    private long id;
    private CategoryRequest category;
    private String name;
    private List<String> photoUrls;
    private TagRequest tags;
    private String status;

}
