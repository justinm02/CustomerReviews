package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.JPA.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
