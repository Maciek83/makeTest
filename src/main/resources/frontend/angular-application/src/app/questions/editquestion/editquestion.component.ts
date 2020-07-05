import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../question.service';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { QuestionEditModel, AnswerEditModel } from '../question.models';

@Component({
  selector: 'app-editquestion',
  templateUrl: './editquestion.component.html',
  styleUrls: ['./editquestion.component.css']
})
export class EditquestionComponent implements OnInit {
  questionForm: FormGroup;

  constructor(public questionService:QuestionService) { }

  ngOnInit(): void {
    this.questionForm = new FormGroup({
      'content': new FormControl(this.questionService.getSelectedQuestion().content, Validators.required),
      'answerEditDto' : new FormArray([]),
      'answerCreateDto' : new FormArray([])
    })

    this.questionService.getSelectedQuestion().answers.forEach(a => {
      this.addExistingAnswers(a);
    });
  }

  getAnswerEditDtoControls(){
    return (<FormArray>this.questionForm.get('answerEditDto')).controls;
  }

  getAnswerCreateDtoControls(){
    return (<FormArray>this.questionForm.get('answerCreateDto')).controls;
  }

  onRemoveAnswerEditDtoControls(id:number)
  {
    (<FormArray>this.questionForm.get('answerEditDto')).removeAt(id);
  }
  
  addExistingAnswers(existingAnswer: AnswerEditModel){
    (<FormArray>this.questionForm.get('answerEditDto')).push(new FormGroup({
      'id': new FormControl(existingAnswer.id),
      'correct': new FormControl(existingAnswer.correct),
      'content': new FormControl(existingAnswer.content, Validators.required)
    }))
  }

  onRemoveQuestionCreateDtoControls(id:number)
  {
    (<FormArray>this.questionForm.get('answerCreateDto')).removeAt(id);
  }

  onAddQuestionCreateDtoControls()
  {
    (<FormArray>this.questionForm.get('answerCreateDto')).push(new FormGroup({
      'correct': new FormControl(false),
      'content': new FormControl(null, Validators.required)
    }));
  }

  onSubmit(){
    const model = new QuestionEditModel(
      this.questionForm.value.content,
      this.questionForm.value.answerEditDto,
      this.questionForm.value.answerCreateDto
    );

    console.log(model);
    //this.questionService.addQuestion(model).subscribe();
    this.clearForm();
  }

  private clearForm() {
    for (const key in this.questionForm.controls) {
      this.questionForm.get(key).clearValidators();
      this.questionForm.get(key).updateValueAndValidity();
    }
    
    this.questionForm.reset();
    
    const formarrayAnswerEditDto = (<FormArray>this.questionForm.get('answerEditDto'));
    while (formarrayAnswerEditDto.length !== 0) {
      formarrayAnswerEditDto.removeAt(0);
    }

    const formarrayAnswerCreateDto = (<FormArray>this.questionForm.get('answerCreateDto'));
    while (formarrayAnswerCreateDto.length !== 0) {
      formarrayAnswerCreateDto.removeAt(0);
    }

  }

}
