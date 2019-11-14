package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.JPA.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
