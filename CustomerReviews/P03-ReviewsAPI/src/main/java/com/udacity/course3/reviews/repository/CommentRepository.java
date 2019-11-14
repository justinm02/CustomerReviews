package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.JPA.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
