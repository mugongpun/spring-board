package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String condition);

    Page<Question> findAll(Pageable pageable);

    @Query("select q from Question q join fetch q.answerList where q.id = :id")
    List<Question> getQuestionAndAnswerList(@Param("id") Integer id);

}
