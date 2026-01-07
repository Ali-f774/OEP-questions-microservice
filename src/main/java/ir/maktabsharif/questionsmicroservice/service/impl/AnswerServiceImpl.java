package ir.maktabsharif.questionsmicroservice.service.impl;

import ir.maktabsharif.questionsmicroservice.model.Answer;
import ir.maktabsharif.questionsmicroservice.model.Question;
import ir.maktabsharif.questionsmicroservice.repository.AnswerRepository;
import ir.maktabsharif.questionsmicroservice.service.AnswerService;
import ir.maktabsharif.questionsmicroservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository repository;
    private final QuestionService questionService;

    @Override
    public Answer addOrUpdate(Answer answer) {
        return repository.save(answer);
    }

    @Override
    public Answer findById(String id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Answer> findExamStudentAnswers(Long studentId, Long examId) {
        List<Question> questions = questionService.findByExamId(examId);
        return repository.findAllByStudentIdAndQuestionIn(studentId,questions);
    }

    @Override
    public Answer findByStudentIdAndQuestionId(Long studentId, String questionId) {
        return repository.findByStudentIdAndQuestion_Id(studentId, questionId);
    }
}
