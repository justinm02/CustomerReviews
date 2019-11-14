package com.udacity.course3.reviews;

import com.udacity.course3.reviews.JPA.Comment;
import com.udacity.course3.reviews.JPA.Product;
import com.udacity.course3.reviews.JPA.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.tokens.CommentToken;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.contentOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired private DataSource dataSource;
	@Autowired private JdbcTemplate jdbcTemplate;
	@Autowired private EntityManager entityManager;
	@Autowired private TestEntityManager testEntityManager;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(commentRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(productRepository).isNotNull();
	}

	@Test
	public void testPostProduct() {
		Product product = new Product();
		product.setProductName("product");
		productRepository.save(product);

		Product actual = productRepository.findById(product.getProductId()).get();

		assertThat(actual).isNotNull();
		assertEquals(actual, product);
	}

	@Test
	public void testPostReview() {
		Product product = new Product();
		product.setProductName("product");
		productRepository.save(product);

		Review review = new Review();
		review.setRate(5);
		review.setProduct(product);
		reviewRepository.save(review);

		Review actual = reviewRepository.findById(review.getReviewID()).get();

		assertThat(actual).isNotNull();
		assertEquals(actual, review);
	}

	@Test
	public void testPostComment() {
		Product product = new Product();
		product.setProductName("product");
		productRepository.save(product);

		Review review = new Review();
		review.setRate(5);
		review.setProduct(product);
		reviewRepository.save(review);

		Comment comment = new Comment();
		comment.setCommentContent("nice product!");
		comment.setReview(review);
		commentRepository.save(comment);

		Comment actual = commentRepository.findById(comment.getCommentId()).get();

		assertThat(actual).isNotNull();
		assertEquals(actual, comment);
	}

	@Test
	public void testProductFindById(){
		Product product = new Product();
		product.setProductName("Thermos");
		entityManager.persist(product);


		Optional<Product> actual = productRepository.findById(1L);
		assertThat(actual).isNotNull();
		assertEquals(product.getProductId(), actual.get().getProductId());
	}

	@Test
	public void testReviewFindById(){
		Product product = new Product();
		product.setProductName("Tape");
		entityManager.persist(product);

		Review review = new Review();
		review.setRate(5);
		review.setProduct(product);
		entityManager.persist(review);

		Optional<Review> actual = reviewRepository.findById(1L);
		assertThat(actual).isNotNull();
		assertEquals(review.getReviewID(), actual.get().getReviewID());
	}

	@Test
	public void testCommentFindById() {
		Product product = new Product();
		product.setProductName("product");
		entityManager.persist(product);

		Review review = new Review();
		review.setRate(5);
		review.setProduct(product);
		entityManager.persist(review);

		Comment comment = new Comment();
		comment.setCommentContent("nice!");
		comment.setReview(review);
		entityManager.persist(comment);

		Optional<Comment> actual = commentRepository.findById(1L);
		assertThat(actual).isNotNull();
		assertEquals(comment.getCommentId(), actual.get().getCommentId());
	}

	@Test
	public void testProductsList() {
		List<Product> listProducts = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Product product = new Product();
			product.setProductName("Thermos");
			entityManager.persist(product);
			listProducts.add(product);
		}

		List<Product> actual = (List<Product>)(productRepository.findAll());

		assertThat(actual).isNotNull();
		assertEquals(actual.get(2), listProducts.get(2));
	}

	@Test
	public void testReviewsList() {
		Product product = new Product();
		product.setProductName("product");
		entityManager.persist(product);

		Review review1 = new Review();
		review1.setRate(3);
		review1.setProduct(product);

		Review review2 = new Review();
		review2.setRate(4);
		review2.setProduct(product);

		Review review3 = new Review();
		review3.setRate(5);
		review3.setProduct(product);

		entityManager.persist(review1);
		entityManager.persist(review2);
		entityManager.persist(review3);

		product.getReviews().add(review1);
		product.getReviews().add(review2);
		product.getReviews().add(review3);

		Product actual = productRepository.findById(product.getProductId()).get();
		assertThat(actual).isNotNull();
		assertEquals(review1.getRate(), actual.getReviews().get(0).getRate());
	}

	@Test
	public void testCommentsList() {
		Product product = new Product();
		product.setProductName("product");
		entityManager.persist(product);

		Review review = new Review();
		review.setRate(5);
		review.setProduct(product);

		entityManager.persist(review);

		product.getReviews().add(review);

		Comment comment = new Comment();
		comment.setCommentContent("Nice product!");
		comment.setReview(review);

		entityManager.persist(comment);
		review.getComments(review.getReviewID()).add(comment);

		Review actual = reviewRepository.findById(review.getReviewID()).get();

		assertThat(actual).isNotNull();
		assertEquals(comment.getCommentContent(), actual.getComments(review.getReviewID()).get(0).getCommentContent());
	}
}