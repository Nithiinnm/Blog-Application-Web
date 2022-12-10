package com.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blogapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query(nativeQuery = true, value = "select c.* from comments c inner join posts p where p.id = c.post_id and created_by =:userId")
	List<Comment> findCommentByUser(Long userId);

}
