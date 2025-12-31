package ir.maktabsharif.questionsmicroservice.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("question")
@Getter
@Setter
@TypeAlias("essay")
public class EssayQuestion extends Question{

    @Override
    public QuestionType getType(Question question) {
        return QuestionType.ESSAY;
    }
}
