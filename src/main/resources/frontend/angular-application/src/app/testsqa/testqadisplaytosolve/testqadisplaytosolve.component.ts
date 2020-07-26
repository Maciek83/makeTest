import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TestDisplaySolveModel, TestSoveModel } from '../test.models';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { QuestionSolveModel, AnswerSolveModel } from 'src/app/questions/question.models';

@Component({
  selector: 'app-testqadisplaytosolve',
  templateUrl: './testqadisplaytosolve.component.html',
  styleUrls: ['./testqadisplaytosolve.component.css']
})
export class TestqadisplaytosolveComponent implements OnInit {

  displayModel: TestDisplaySolveModel;
  testSolveForm: FormGroup;
  solveModel: TestSoveModel;

  constructor(private route: ActivatedRoute) { }

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
    });

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
          questionSolveModel.answers.push(new AnswerSolveModel(a.value.id, a.value.correct))
      })
      
      this.solveModel.questions.push(questionSolveModel);
    });
    
    console.log(this.solveModel);
  }
}