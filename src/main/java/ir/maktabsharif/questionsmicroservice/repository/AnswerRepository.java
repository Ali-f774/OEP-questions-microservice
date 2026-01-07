package ir.maktabsharif.questionsmicroservice.repository;

import ir.maktabsharif.questionsmicroservice.model.Answer;
import ir.maktabsharif.questionsmicroservice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerRepository extends MongoRepository<Answer,String> {

    List<Answer> findAllByStudentIdAndQuestionIn(Long studentId, List<Question> questions);

    Answer findByStudentIdAndQuestion_Id(Long studentId, String questionId);
}
