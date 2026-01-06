package ir.maktabsharif.questionsmicroservice.service;

import ir.maktabsharif.questionsmicroservice.model.Answer;

import java.util.List;

public interface AnswerService {

    Answer addOrUpdate(Answer answer);

    Answer findById(String id);

    List<Answer> findExamStudentAnswers(Long studentId,Long examId);

    Answer findByStudentIdAndQuestionId(Long studentId,String questionId);

}
