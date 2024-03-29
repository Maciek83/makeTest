import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl, NgForm } from '@angular/forms';
import { QuestionCreateModel } from '../question.models';
import { QuestionService } from '../question.service';


@Component({
  selector: 'app-addquestion',
  templateUrl: './addquestion.component.html',
  styleUrls: ['./addquestion.component.css']
})
export class AddquestionComponent implements OnInit {
  questionForm: FormGroup;
  @ViewChild('f') questionNgForm: NgForm;
  

  constructor(private questionService:QuestionService){ }

  ngOnInit(): void {
    this.questionForm = new FormGroup({
      'content': new FormControl(null, Validators.required),
      'answers' : new FormArray([])
    },[this.numberOfQuestionsValidator])
  }

  onSubmit(){
    const model = new QuestionCreateModel(
      this.questionForm.value.content,
      this.questionForm.value.answers
    );

    this.questionService.addQuestion(model).subscribe();
    this.clearForm();
  }

  private clearForm() {
    this.questionNgForm.resetForm();
    
    const formarray = (<FormArray>this.questionForm.get('answers'));
    while (formarray.length !== 0) {
      formarray.removeAt(0);
    }
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

  numberOfQuestionsValidator(control: AbstractControl): { [key: string]: boolean } | null {
    
    const formArray = (<FormArray>control.get('answers'));
    
    let x = 0;

    if(formArray.length < 2)
    {
      return {'answers': false};
    }

    for(let i=0; i<formArray.length; i++){
      if(formArray.controls[i].value.correct === true){
        x++;
      }
    }

    if(x === 0)
    {
      return {'answers': false};
    }

    return null;
  }
}
