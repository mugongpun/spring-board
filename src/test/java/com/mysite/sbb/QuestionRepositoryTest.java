package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback(value = false)
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    EntityManager em;


    @Test
    void testJpa() throws Exception {
        //given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다");
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링 부트 모델 질문입니다");
        q2.setContent("id왜 자동 생성 되나요?");

        questionRepository.save(q2);

        //when

        //then

    }

    @Test
    void findAllTest() throws Exception {

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다");
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링 부트 모델 질문입니다");
        q2.setContent("id왜 자동 생성 되나요?");

        questionRepository.save(q2);
        //given
        Question oq = questionRepository.findBySubject(q1.getSubject());
        assertEquals("sbb가 무엇인가요?", oq.getSubject());

        Question findQ = questionRepository.findBySubjectAndContent(q1.getSubject(), q1.getContent());
        Assertions.assertThat(findQ)
                  .isEqualTo(q1);
        //when

        //then

    }

    @Test
    void jpaLikeTest() throws Exception {

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다");
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링 부트 모델 질문입니다");
        q2.setContent("id왜 자동 생성 되나요?");

        questionRepository.save(q2);

        List<Question> result = questionRepository.findBySubjectLike("sbb%");//이렇게 like조건을 줘야함
        for (Question question : result) {
            System.out.println("question = " + question);
        }
    }

    @Test
    void changeQuestion() throws Exception {

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다");
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링 부트 모델 질문입니다");
        q2.setContent("id왜 자동 생성 되나요?");

        questionRepository.save(q2);

        em.flush();
        em.clear();

        assertEquals(2, questionRepository.count());
        Optional<Question> oq = questionRepository.findById(1);
        Question q = oq.get();
        questionRepository.delete(q);
        assertEquals(1, questionRepository.count());


    }

    @Test
    void answerTest() throws Exception {

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다");
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링 부트 모델 질문입니다");

        q2.setContent("id왜 자동 생성 되나요?");

        questionRepository.save(q2);


        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다");
        a.setQuestion(q1);
        answerRepository.save(a);

//        q1.getAnswerList().stream().forEach(o -> System.out.println(o));

        em.flush();
        em.clear();

        Answer findA = answerRepository.findById(a.getId())
                                        .get();
//        assertEquals(1, findA.getQuestion()
//                             .getId());

        System.out.println(findA.getQuestion()
                                .getId());
    }


    @Test
    void getAnswerFromQuestion() throws Exception {
        //given
        Optional<Question> oq = questionRepository.findById(2);
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();
        assertEquals(1, answerList.size());
        for (Answer answer : answerList) {
            System.out.println(answer.getContent());
        }

        //when

        //then

    }



}