package ir.maktabsharif.questionsmicroservice.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Question {

    @Id
    private String id;

    private String title;

    private String question;

    private Double grade;

    private Long courseId;

    private Long professorId;

    private Long examId;

    private QuestionType type;

    public abstract QuestionType getType(Question question);



}
