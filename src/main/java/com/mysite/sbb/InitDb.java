package com.mysite.sbb;


import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

    @PostConstruct
    public void initDb() {
        for (int i = 0; i < 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i+1);
            String content = "테스트용 내용";
            questionService.create(subject, content, null);
        }
    }
}
