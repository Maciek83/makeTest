import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { QuestionCreateModel } from '../question.models';
import { QuestionService } from '../question.service';
import { catchError, retry } from 'rxjs/operators';
import { throwError } from 'rxjs/internal/observable/throwError';

@Component({
  selector: 'app-addquestion',
  templateUrl: './addquestion.component.html',
  styleUrls: ['./addquestion.component.css']
})
export class AddquestionComponent implements OnInit {
  questionForm: FormGroup;
  public errorMessage:string = '';

  constructor(private questionServiec:QuestionService){ }

  ngOnInit(): void {
    this.questionForm = new FormGroup({
      'content': new FormControl(null, Validators.required),
      'answers' : new FormArray([])
    })
  }

  onSubmit(){
    const model = new QuestionCreateModel(
      this.questionForm.value.content,
      this.questionForm.value.answers
    );

    this.questionForm.reset();

    const formarray = (<FormArray>this.questionForm.get('answers'));
    while(formarray.length !== 0){
      formarray.removeAt(0);
    }

    console.log(model);
    this.questionServiec.addQuestion(model)
    .pipe(
      retry(1),
      catchError(this.handleError))
    .subscribe();
  }

  onRemoveQuestion(id:number)
  {
    (<FormArray>this.questionForm.get('answers')).removeAt(id);
  }

  onAddQuestion()
  {
    (<FormArray>this.questionForm.get('answers')).push(new FormGroup({
      'correct': new FormControl(false),
      'content': new FormControl(null, Validators.required)
    }));
  }

  getControls(){
    return (<FormArray>this.questionForm.get('answers')).controls;
  }

  handleError(error) {
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      this.errorMessage = error.error.message;
    } else {
      // Get server-side error
      this.errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(this.errorMessage);
  }
}
