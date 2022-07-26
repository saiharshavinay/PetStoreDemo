package com.petstore.demo.Response;

import lombok.*;


import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity implements Serializable {
    private String status;
    private String statusMessage;
    private Object response;
}
