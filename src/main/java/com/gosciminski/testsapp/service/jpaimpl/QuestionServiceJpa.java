package com.gosciminski.testsapp.service.jpaimpl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.gosciminski.testsapp.converter.QuestionCreateDtoToQuestion;
import com.gosciminski.testsapp.converter.QuestionToQuestionDisplayDto;
import com.gosciminski.testsapp.dto.create.QuestionCreateDto;
import com.gosciminski.testsapp.dto.display.QuestionDisplayDto;
import com.gosciminski.testsapp.dto.edit.QuestionEditDto;
import com.gosciminski.testsapp.exceptions.QuestionException;
import com.gosciminski.testsapp.model.Answer;
import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.repisitory.QuestionRepository;
import com.gosciminski.testsapp.service.QuestionService;

import org.springframework.stereotype.Service;

@Service
public class QuestionServiceJpa implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionToQuestionDisplayDto questionToQuestionDisplayDto;
    private final QuestionCreateDtoToQuestion questionCreateDtoToQuestion;

    public QuestionServiceJpa(QuestionRepository questionRepository,
            QuestionToQuestionDisplayDto questionToQuestionDisplayDto,
            QuestionCreateDtoToQuestion questionCreateDtoToQuestion) {
        this.questionRepository = questionRepository;
        this.questionToQuestionDisplayDto = questionToQuestionDisplayDto;
        this.questionCreateDtoToQuestion = questionCreateDtoToQuestion;
    }

    @Override
    public List<QuestionDisplayDto> findAll() {
        Iterable<Question> questions = questionRepository.findAll();
        List<QuestionDisplayDto> questionsDisplayDto = new LinkedList<>();
        questions.forEach(q -> questionsDisplayDto.add(questionToQuestionDisplayDto.convert(q)));
        return questionsDisplayDto;
    }

    @Override
    public QuestionDisplayDto save(QuestionCreateDto createDto) throws QuestionException {

        if (createDto.getAnswers().size() < 2) {
            throw new QuestionException("Not enough answers in question. Add at least two answers.");
        }

        if (!createDto.getAnswers().stream().anyMatch(t -> t.getCorrect() == true)) {
            throw new QuestionException("At least one answer must be true.");
        }

        Question tosave = questionCreateDtoToQuestion.convert(createDto);
        Question savedInDb = questionRepository.save(tosave);
        return questionToQuestionDisplayDto.convert(savedInDb);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    @Override
    public QuestionDisplayDto findQuestionDisplayDtoByid(Long id) {
        return questionToQuestionDisplayDto.convert(findById(id));
    }

    @Override
    public QuestionDisplayDto update(Long id, QuestionEditDto editDto) throws QuestionException {
        
        if (editDto.getAnswerCreateDto().size() + editDto.getAnswerEditDto().size() < 2) {
            throw new QuestionException("Not enough answers in question. Add at least two answers.");
        }

        if (!editDto.getAnswerCreateDto().stream().anyMatch(t -> t.getCorrect() == true) &&
            !editDto.getAnswerEditDto().stream().anyMatch(t -> t.getCorrect() == true)) {
            throw new QuestionException("At least one answer must be true.");
        }

        Question questionFromDb = findById(id);

        questionFromDb.setContent(editDto.getContent());

        deleteExistingAnswers(editDto, questionFromDb);
        editExistingAnswers(editDto, questionFromDb);
        createNewAnswers(editDto, questionFromDb);

        Question questionAfterSaving = questionRepository.save(questionFromDb);

        return questionToQuestionDisplayDto.convert(questionAfterSaving);
    }

    private void createNewAnswers(QuestionEditDto editDto, Question questionFromDb) {
        editDto.getAnswerCreateDto().forEach( ac -> {
            Answer newAnswer = new Answer();
            newAnswer.setContent(ac.getContent());
            newAnswer.setCorrect(ac.getCorrect());
            newAnswer.setQuestion(questionFromDb);

            questionFromDb.getAnswers().add(newAnswer);
        });
    }

    private void editExistingAnswers(QuestionEditDto editDto, Question questionFromDb) {
        questionFromDb.getAnswers().forEach(a -> editDto.getAnswerEditDto().stream().forEach(
            ae -> {
                if(ae.getId() == a.getId()){
                    a.setContent(ae.getContent());
                    a.setCorrect(ae.getCorrect());
                }
            }
        ));
    }

    private void deleteExistingAnswers(QuestionEditDto editDto, Question questionFromDb) {
        List<Long> listOfIds = editDto.getAnswerEditDto()
        .stream()
        .map(ae->ae.getId()).collect(Collectors.toList());

        questionFromDb.getAnswers()
        .removeIf(a->!listOfIds.contains(a.getId()));
    }

}