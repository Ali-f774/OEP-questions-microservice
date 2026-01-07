package ir.maktabsharif.questionsmicroservice.dto;

public record AddAnswerDTO(
        String id,
        Long studentId,
        String answer,
        String questionId,
        Double grade
) {
}
