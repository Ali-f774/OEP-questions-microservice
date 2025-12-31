package ir.maktabsharif.questionsmicroservice.dto;

import ir.maktabsharif.questionsmicroservice.model.QuestionType;

public record GeneralQuestionDTO(
        String id,
        String title,
        String question,
        QuestionType type
) {
}
