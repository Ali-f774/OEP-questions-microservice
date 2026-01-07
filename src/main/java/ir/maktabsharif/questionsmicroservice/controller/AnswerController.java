package ir.maktabsharif.questionsmicroservice.controller;

import ir.maktabsharif.questionsmicroservice.dto.AddAnswerDTO;
import ir.maktabsharif.questionsmicroservice.mapper.DataMapper;
import ir.maktabsharif.questionsmicroservice.model.Answer;
import ir.maktabsharif.questionsmicroservice.model.Question;
import ir.maktabsharif.questionsmicroservice.service.AnswerService;
import ir.maktabsharif.questionsmicroservice.service.QuestionService;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/answers")
public class AnswerController {

    private final DataMapper mapper;
    private final QuestionService questionService;
    private final AnswerService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('STUDENT','PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public String addOrUpdateAnswer(@RequestBody AddAnswerDTO dto) {
        Question question;
        try {
            question = questionService.findById(dto.questionId());
        }catch (IllegalArgumentException e){
            throw new BadRequestException("Invalid Question Id");
        }
        Answer answer = mapper.toAnswerEntity(dto);
        Answer existAnswer = service.findByStudentIdAndQuestionId(dto.studentId(), dto.questionId());
        if (existAnswer != null)
            answer.setId(existAnswer.getId());
        answer.setQuestion(question);
        service.addOrUpdate(answer);
        return "Successfully";
    }

    @GetMapping("/student")
    @PreAuthorize("hasAnyRole('STUDENT','PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public AddAnswerDTO getStudentAnswer(@RequestParam Long studentId, @RequestParam String questionId) {
        Answer answer = service.findByStudentIdAndQuestionId(studentId,questionId);
        AddAnswerDTO addAnswerDto = mapper.toAddAnswerDto(answer);
        return addAnswerDto;
    }


    @GetMapping("/exam")
    @PreAuthorize("hasAnyRole('STUDENT','PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public List<AddAnswerDTO> getExamAnswers(@RequestParam Long studentId, @RequestParam Long examId) {
        List<Answer> answers = service.findExamStudentAnswers(studentId, examId);
        List<AddAnswerDTO> list = answers.stream().map(mapper::toAddAnswerDto).toList();
        return list;
    }

}
