package com.udacity.course3.reviews.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long reviewId;

    @Column(name = "RATE")
    private int rate;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @OneToMany(mappedBy = "review")
    private List<Comment> comments = new ArrayList<Comment>();

    public Long getReviewID() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public List<Comment> getComments(Long reviewId) {
        return comments;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct(Long reviewId) {
        return product;
    }
}