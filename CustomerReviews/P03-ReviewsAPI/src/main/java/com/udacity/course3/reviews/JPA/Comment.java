package com.udacity.course3.reviews.JPA;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @Column(name = "CONTENT")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Review getReview(Long commentId) {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
