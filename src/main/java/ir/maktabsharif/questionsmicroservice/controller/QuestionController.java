package ir.maktabsharif.questionsmicroservice.controller;

import ir.maktabsharif.questionsmicroservice.dto.AddQuestionDTO;
import ir.maktabsharif.questionsmicroservice.dto.GeneralQuestionDTO;
import ir.maktabsharif.questionsmicroservice.mapper.DataMapper;
import ir.maktabsharif.questionsmicroservice.model.Question;
import ir.maktabsharif.questionsmicroservice.model.QuestionType;
import ir.maktabsharif.questionsmicroservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService service;
    private final DataMapper mapper;

    @GetMapping("/general")
    @PreAuthorize("hasRole('PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public List<GeneralQuestionDTO> courseQuestionBank(@RequestParam Long courseId,@RequestParam Long professorId){
        return mapper.allToGeneralDto(service.findNotUsedExams(courseId, professorId));
    }


    @GetMapping("/exist")
    @PreAuthorize("hasRole('PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public Boolean existQuestion(@RequestParam Long courseId,@RequestParam Long professorId,@RequestParam String title){
        return service.questionExist(courseId, professorId, title);
    }


    @DeleteMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public String deleteQuestion(@RequestParam String id){
        service.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('PROFESSOR','STUDENT')")
    @ResponseStatus(HttpStatus.OK)
    public AddQuestionDTO findById(@RequestParam String id){
        Question byId = service.findById(id);
        return mapper.toAddDto(byId);
    }

    @GetMapping("/exam")
    @PreAuthorize("hasAnyRole('PROFESSOR','STUDENT')")
    @ResponseStatus(HttpStatus.OK)
    public List<AddQuestionDTO> findByExamId(@RequestParam Long examId){
        List<Question> list = service.findByExamId(examId);
        return mapper.allToAddDto(list);
    }
    @GetMapping("/not-used")
    @PreAuthorize("hasRole('PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public List<AddQuestionDTO> findNotUsed(@RequestParam Long courseId,@RequestParam Long professorId){
        List<Question> list = service.findNotUsedExams(courseId, professorId);
        return mapper.allToAddDto(list);
    }


    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    public String addQuestion(@RequestBody AddQuestionDTO dto){
        Question question = null;
        if (dto.type() == QuestionType.ESSAY){
            question = mapper.toEssayEntity(dto);
        }
        if (dto.type() == QuestionType.MULTI_CHOICE){
            question = mapper.toMultiEntity(dto);
        }
        if (question == null){
            throw new IllegalArgumentException("Invalid Type");
        }
        service.addOrUpdate(question);
        return "Successfully Added";
    }
}
