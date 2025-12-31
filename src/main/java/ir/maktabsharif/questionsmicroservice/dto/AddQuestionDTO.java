package ir.maktabsharif.questionsmicroservice.dto;

import ir.maktabsharif.questionsmicroservice.model.QuestionType;

import java.util.List;

public record AddQuestionDTO(
    String id,
    String title,
    String question,
    QuestionType type,
    List<String> choices,
    String correctChoice,
    Long courseId,
    Long professorId,
    Double grade,
    Long examId
) {
}
