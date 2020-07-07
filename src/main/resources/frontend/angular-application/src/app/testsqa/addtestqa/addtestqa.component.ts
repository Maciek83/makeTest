import { Component, OnInit } from '@angular/core';
import { QuestionService } from 'src/app/questions/question.service';
import { TestService } from '../test.service';
import { QuestionDisplayModel } from 'src/app/questions/question.models';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';
import { TestAddModel } from '../test.models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addtestqa',
  templateUrl: './addtestqa.component.html',
  styleUrls: ['./addtestqa.component.css']
})
export class AddtestqaComponent implements OnInit {
  testForm: FormGroup;
  model: TestAddModel;
  public questionsToAdd: QuestionDisplayModel[];
  public questionsAdded: QuestionDisplayModel[];

  constructor(public questionService: QuestionService, private testService: TestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.questionsToAdd = this.route.snapshot.data.questions;

    this.model = new TestAddModel("", []);   
    this.testForm = new FormGroup({
      'name': new FormControl(null, Validators.required)
    });
  }

  addQuestionIdToModel(id: number) {
    this.model.questionsIds.push(id);
    let question = this.questionsToAdd.find(q=>q.id===id);
    this.questionsAdded.push(question);
  }

  removeQuestionIdFromModel(id: number) {
    for (let i = 0; i < this.model.questionsIds.length; i++) {
      if (this.model.questionsIds[i] === id) {
        this.model.questionsIds.splice(i--, 1);
      }
    }

    for (let i = 0; i < this.questionsAdded.length; i++) {
      if (this.questionsAdded[i].id === id) {
        this.questionsAdded.splice(i--, 1);
      }
    }
  }

  canAddQuestion(id: number): boolean {
    return this.model.questionsIds.includes(id);
  }

  numberOfQuestionsValidator(): boolean {
    return this.model.questionsIds.length <=0;
  }

  onSubmit() {

  }

}
