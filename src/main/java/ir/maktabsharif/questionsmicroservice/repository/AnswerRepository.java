package ir.maktabsharif.questionsmicroservice.repository;

import ir.maktabsharif.questionsmicroservice.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerRepository extends MongoRepository<Answer,String> {

    List<Answer> findAllByStudentIdAndQuestion_ExamId(Long studentId, Long examId);

    Answer findByStudentIdAndQuestion_Id(Long studentId, String questionId);
}
