package ir.maktabsharif.questionsmicroservice.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TypeAlias("multichoice")
public class MultiChoiceQuestion extends Question{

    private List<String> choices;

    private String correctChoice;

    @Override
    public QuestionType getType(Question question) {
        return QuestionType.MULTI_CHOICE;
    }
}
