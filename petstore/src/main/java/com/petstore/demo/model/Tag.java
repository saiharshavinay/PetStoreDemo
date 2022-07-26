package com.petstore.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tag")
@NoArgsConstructor
@Getter
@Setter
public class Tag {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id ")
    private long id;

    @Column(name="tag_name")
    private String name;
}
