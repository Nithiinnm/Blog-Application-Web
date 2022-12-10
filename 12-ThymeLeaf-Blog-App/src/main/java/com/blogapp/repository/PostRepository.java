package com.blogapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blogapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	Optional<Post> findByUrl(String url);
	
//	"SELECT * FROM products p WHERE p.name LIKE CONCAT('%', :query, '%')" +
//	"Or p.description LIKE CONCAT('%', :query, '%')"
	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :query, '%')" +
//			"Or p.description LIKE CONCAT('%', :query, '%')")
	
	
	@Query(nativeQuery = true, value = "Select * from post p where p.title LIKE CONCAT('%', :query, '%')" +
			" Or p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Post> searchPostByNativeQ(String query);
	
	
	@Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :query, '%')" +
			"Or p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Post> searchPostHQL(String query);
	
	@Query(nativeQuery = true, value = "select * from posts p where p.created_by =:userId")
	List<Post> findPostByUser(Long userId);

}
