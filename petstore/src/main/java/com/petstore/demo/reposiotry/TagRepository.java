package com.petstore.demo.reposiotry;

import com.petstore.demo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag,Long> {
}
