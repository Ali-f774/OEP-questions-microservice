package ir.maktabsharif.questionsmicroservice.mapper;

import ir.maktabsharif.questionsmicroservice.dto.AddAnswerDTO;
import ir.maktabsharif.questionsmicroservice.dto.AddQuestionDTO;
import ir.maktabsharif.questionsmicroservice.dto.GeneralQuestionDTO;
import ir.maktabsharif.questionsmicroservice.model.Answer;
import ir.maktabsharif.questionsmicroservice.model.EssayQuestion;
import ir.maktabsharif.questionsmicroservice.model.MultiChoiceQuestion;
import ir.maktabsharif.questionsmicroservice.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DataMapper {

    GeneralQuestionDTO toGeneralDto(Question question);

    List<GeneralQuestionDTO> allToGeneralDto(List<Question> questions);

    MultiChoiceQuestion toMultiEntity(AddQuestionDTO dto);
    EssayQuestion toEssayEntity(AddQuestionDTO dto);

    @SubclassMapping(source = MultiChoiceQuestion.class, target = AddQuestionDTO.class)
    @SubclassMapping(source = EssayQuestion.class, target = AddQuestionDTO.class)
    AddQuestionDTO toAddDto(Question question);

    List<AddQuestionDTO> allToAddDto(List<Question> list);

    @Mapping(target = "question",ignore = true)
    Answer toAnswerEntity(AddAnswerDTO dto);

    @Mapping(target = "questionId",ignore = true)
    AddAnswerDTO toAddAnswerDto(Answer answer);
}
