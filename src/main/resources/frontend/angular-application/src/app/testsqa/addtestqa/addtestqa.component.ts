import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { QuestionService } from 'src/app/questions/question.service';
import { TestService } from '../test.service';
import { QuestionDisplayModel } from 'src/app/questions/question.models';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { TestAddModel } from '../test.models';
import { ActivatedRoute } from '@angular/router';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'app-addtestqa',
  templateUrl: './addtestqa.component.html',
  styleUrls: ['./addtestqa.component.css']
})
export class AddtestqaComponent implements OnInit {
  displayedColumns: string[] = ['name', 'add'];
  testForm: FormGroup;
  model: TestAddModel;
  public questionsToAdd: QuestionDisplayModel[];
  public questionsAdded: QuestionDisplayModel[] = [];
  @ViewChild('f') testNgForm: NgForm;
  @ViewChild(MatTable, { static: true }) addedTableQuestions: MatTable<any>;

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
    let question = this.questionsToAdd.find(q => q.id === id);
    this.questionsAdded.push(question);
    this.addedTableQuestions.renderRows();
  }

  removeQuestionIdFromModelAndTable(id: number) {
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

    this.addedTableQuestions.renderRows();
  }

  canAddQuestion(id: number): boolean {
    return this.model.questionsIds.includes(id);
  }

  numberOfQuestionsValidator(): boolean {
    return this.model.questionsIds.length <= 0;
  }

  ceateFormGroup() {
    this.testForm = new FormGroup({
      'name': new FormControl(null, Validators.required)
    });
  }

  clearForm() {

    this.testNgForm.resetForm();

    this.model = new TestAddModel("", []);
    this.questionsAdded = [];
    this.addedTableQuestions.renderRows();
  }

  onSubmit() {
    this.model.name = this.testForm.value.name;
    this.testService.addTest(this.model).subscribe();

    this.clearForm()
  }

}
