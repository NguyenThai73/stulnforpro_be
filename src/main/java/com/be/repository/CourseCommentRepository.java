package com.be.repository;

import com.be.common_api.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCommentRepository extends JpaRepository<CourseComment, Long>, JpaSpecificationExecutor<CourseComment> {
}