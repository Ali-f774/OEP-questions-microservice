package ir.maktabsharif.questionsmicroservice.repository;

import ir.maktabsharif.questionsmicroservice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question,String> {
    Optional<Question> findByTitle(String title);
    List<Question> findAllByCourseIdAndProfessorId(Long courseId,Long professorId);
    List<Question> findAllByExamId(Long examId);
    List<Question> findAllByExamIdIsNullAndCourseIdAndProfessorId(Long courseId,Long professorId);
    Boolean existsByCourseIdAndProfessorIdAndTitleAndExamIdIsNull(Long courseId, Long professorId, String title);
}
