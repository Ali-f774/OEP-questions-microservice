package ir.maktabsharif.questionsmicroservice.service;

import ir.maktabsharif.questionsmicroservice.dto.GeneralReqDTO;
import ir.maktabsharif.questionsmicroservice.model.Question;

import java.util.List;

public interface QuestionService {

    Question addOrUpdate(Question question);

    Question findById(String id);

    Question findByTitle(String title);

    List<Question> findAll();

    List<Question> findByExamId(Long examId);

    List<Question> findNotUsedExams(Long courseId,Long professorId);

    List<Question> findAllGenerals(GeneralReqDTO dto);

    Boolean questionExist(Long courseId,Long professorId,String title);

    void deleteById(String id);
}
