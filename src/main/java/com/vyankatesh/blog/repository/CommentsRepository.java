package com.vyankatesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vyankatesh.blog.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer>{

	List<Comments> findByPostPostId (Integer postId);
}
