import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../question.service';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';
import { QuestionEditModel, AnswerEditModel, QuestionDisplayModel } from '../question.models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-editquestion',
  templateUrl: './editquestion.component.html',
  styleUrls: ['./editquestion.component.css']
})
export class EditquestionComponent implements OnInit {
  questionForm: FormGroup;
  id: number;

  constructor(public questionService: QuestionService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.questionService.fetchQuestionToEdit(this.id);

    this.questionForm = new FormGroup({
      'content': new FormControl(this.questionService.getSelectedQuestion().content, Validators.required),
      'answerEditDto': new FormArray([]),
      'answerCreateDto': new FormArray([])
    },[this.numberOfQuestionsValidator])

    this.questionService.getSelectedQuestion().answers.forEach(a => {
      this.addExistingAnswers(a);
    });
  }

  getAnswerEditDtoControls() {
    return (<FormArray>this.questionForm.get('answerEditDto')).controls;
  }

  getAnswerCreateDtoControls() {
    return (<FormArray>this.questionForm.get('answerCreateDto')).controls;
  }

  onRemoveAnswerEditDtoControls(id: number) {
    (<FormArray>this.questionForm.get('answerEditDto')).removeAt(id);
  }

  addExistingAnswers(existingAnswer: AnswerEditModel) {
    (<FormArray>this.questionForm.get('answerEditDto')).push(new FormGroup({
      'id': new FormControl(existingAnswer.id),
      'correct': new FormControl(existingAnswer.correct),
      'content': new FormControl(existingAnswer.content, Validators.required)
    }))
  }

  onRemoveQuestionCreateDtoControls(id: number) {
    (<FormArray>this.questionForm.get('answerCreateDto')).removeAt(id);
  }

  onAddQuestionCreateDtoControls() {
    (<FormArray>this.questionForm.get('answerCreateDto')).push(new FormGroup({
      'correct': new FormControl(false),
      'content': new FormControl(null, Validators.required)
    }));
  }

  onSubmit() {
    const model = new QuestionEditModel(
      this.questionForm.value.content,
      this.questionForm.value.answerEditDto,
      this.questionForm.value.answerCreateDto
    );

    this.questionService.editQuestion(this.id, model).subscribe();

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

  numberOfQuestionsValidator(control: AbstractControl): { [key: string]: boolean } | null {
    
    const formArrayAnswerEditDto = (<FormArray>control.get('answerEditDto'));
    const formArrayAnswerCreateDto = (<FormArray>control.get('answerCreateDto'));
    
    let x = 0;

    if(formArrayAnswerEditDto.length + formArrayAnswerCreateDto.length < 2)
    {
      return {'answerEditDto': false};
    }

    for(let i=0; i < formArrayAnswerEditDto.length; i++){
      if(formArrayAnswerEditDto.controls[i].value.correct === true){
        x++;
      }
    }

    for(let i=0; i < formArrayAnswerCreateDto.length; i++){
      if(formArrayAnswerCreateDto.controls[i].value.correct === true){
        x++;
      }
    }

    if(x === 0)
    {
      return {'answerEditDto': false};
    }

    return null;
  }

}
