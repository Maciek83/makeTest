import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TestDisplaySolveModel, TestSoveModel, TestSoveInfoModel } from '../test.models';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';
import { QuestionSolveModel, AnsweredAnswerModel } from 'src/app/questions/question.models';
import { TestSolveService } from '../testsolve.service';

@Component({
  selector: 'app-testqadisplaytosolve',
  templateUrl: './testqadisplaytosolve.component.html',
  styleUrls: ['./testqadisplaytosolve.component.css']
})
export class TestqadisplaytosolveComponent implements OnInit {

  displayModel: TestDisplaySolveModel;
  testSolveForm: FormGroup;
  solveModel: TestSoveModel;
  submited: boolean = false;

  constructor(private route: ActivatedRoute, private testSolveService: TestSolveService) { }

  ngOnInit(): void {
    this.solveModel = new TestSoveModel("",0,[]);

    this.displayModel = this.route.snapshot.data.testSolve;
    this.setupForm();
  }

  public getQuestionControlArray(){
    return (<FormArray>this.testSolveForm.get('questions')).controls;
  }

  public getAnswerControlArray(id:number){
    return (<FormArray>this.getQuestionControlArray()[id].get('answers')).controls;
  }

  private setupForm() {

    this.testSolveForm = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'id': new FormControl(this.displayModel.id, Validators.required),
      'questions': new FormArray([])
    },[this.numberOfQuestionsValidator]);

    let questionArray = (<FormArray>this.testSolveForm.get('questions'));

    this.displayModel.questionDisplayDto.forEach(
      (q, index) => {
        questionArray.push(
          new FormGroup({
            'id': new FormControl(q.id),
            'answers': new FormArray([])
          })
        )

        let answers = (<FormArray>questionArray.controls[index].get('answers'));
        
        q.answers.forEach(a => {
          answers.push(
            new FormGroup({
              'id': new FormControl(a.id),
              'correct': new FormControl(false, Validators.required),
            })
          )
        })
      }
    );
  }

  onSubmit(){

    this.solveModel.id = this.testSolveForm.value.id;
    this.solveModel.name = this.testSolveForm.value.name;

    this.getQuestionControlArray().forEach(q =>{
      
      let answers = (<FormArray>q.get('answers')).controls;
      let questionSolveModel = new QuestionSolveModel(q.value.id,[]);
      answers.forEach(a =>{
          questionSolveModel.answeredAnswers.push(new AnsweredAnswerModel(a.value.id, a.value.correct))
      })
      
      this.solveModel.questions.push(questionSolveModel);
    });
    
    this.testSolveService.solveTest(this.solveModel).subscribe
    (
      d => console.log(d)
    );

    this.submited = true;
  }

  numberOfQuestionsValidator(control: AbstractControl): { [key: string]: boolean } | null {
    
    const formArrayQuestion = (<FormArray>control.get('questions')).controls;
    
    let x:number = 0;
    let answers: number = 0;
    let answersValidationModel: AnswersValidationModel[] = [];

    for(let i=0; i < formArrayQuestion.length; i++){
      const formArrayAnswers = (<FormArray>formArrayQuestion[i].get('answers'));
      let validationModel = new AnswersValidationModel();
      answersValidationModel.push(validationModel);
      for(let ii=0 ; ii < formArrayAnswers.length; ii++){
        if(formArrayAnswers.controls[ii].value.correct === true){
          validationModel.numberOfCorrect++;
        }
      }  
    }

    for(let i=0; i < answersValidationModel.length; i++){
      if(answersValidationModel[i].numberOfCorrect === 0){
        return {'answers': false};
      }
    }

    return null;
  }
}

export class AnswersValidationModel{
  numberOfCorrect:number = 0;
}