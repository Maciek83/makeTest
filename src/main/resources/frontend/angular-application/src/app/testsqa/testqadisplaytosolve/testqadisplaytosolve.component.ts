import { Component, OnInit } from '@angular/core';
import { TestShareService } from '../testshare.service';
import { ActivatedRoute } from '@angular/router';
import { TestDisplaySolveModel } from '../test.models';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';

@Component({
  selector: 'app-testqadisplaytosolve',
  templateUrl: './testqadisplaytosolve.component.html',
  styleUrls: ['./testqadisplaytosolve.component.css']
})
export class TestqadisplaytosolveComponent implements OnInit {

  displayModel: TestDisplaySolveModel;
  testSolveForm: FormGroup;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.displayModel = this.route.snapshot.data.testSolve;
    this.setupForm();

    console.log(this.getQuestionControlArray());
    console.log(this.getAnswerControlArray(0));
    console.log(this.displayModel.questionDisplayDto[0]);
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
    console.log(this.testSolveForm);
  }
}