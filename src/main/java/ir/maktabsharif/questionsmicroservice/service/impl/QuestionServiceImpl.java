package ir.maktabsharif.questionsmicroservice.service.impl;

import ir.maktabsharif.questionsmicroservice.dto.GeneralReqDTO;
import ir.maktabsharif.questionsmicroservice.model.EssayQuestion;
import ir.maktabsharif.questionsmicroservice.model.MultiChoiceQuestion;
import ir.maktabsharif.questionsmicroservice.model.Question;
import ir.maktabsharif.questionsmicroservice.model.QuestionType;
import ir.maktabsharif.questionsmicroservice.repository.QuestionRepository;
import ir.maktabsharif.questionsmicroservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;

    @Override
    public Question addOrUpdate(Question question) {
        if (question instanceof MultiChoiceQuestion)
            question.setType(QuestionType.MULTI_CHOICE);

        if (question instanceof EssayQuestion)
            question.setType(QuestionType.ESSAY);
        return repository.save(question);
    }

    @Override
    public Question findById(String id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Question with this id not found"));
    }

    @Override
    public Question findByTitle(String title) {
        return repository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("Question with this title not found"));
    }

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Question> findByExamId(Long examId) {
        return repository.findAllByExamId(examId);
    }

    @Override
    public List<Question> findNotUsedExams(Long courseId, Long professorId) {
        return repository.findAllByExamIdIsNullAndCourseIdAndProfessorId(courseId, professorId);
    }

    @Override
    public List<Question> findAllGenerals(GeneralReqDTO dto) {
        return repository.findAllByCourseIdAndProfessorId(dto.courseId(), dto.professorId());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean questionExist(Long courseId, Long professorId, String title) {
        return repository.existsByCourseIdAndProfessorIdAndTitleAndExamIdIsNull(courseId, professorId, title);
    }
}
