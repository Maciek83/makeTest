import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TestService } from '../test.service';
import { FormGroup, NgForm, FormControl, Validators } from '@angular/forms';
import { TestAddModel } from '../test.models';
import { QuestionDisplayModel } from 'src/app/questions/question.models';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'app-edittestqa',
  templateUrl: './edittestqa.component.html',
  styleUrls: ['./edittestqa.component.css']
})
export class EdittestqaComponent implements OnInit {
  displayedColumns: string[] = ['name', 'add'];
  testForm: FormGroup;
  model: TestAddModel;
  public questionsToAdd: QuestionDisplayModel[];
  public questionsAdded: QuestionDisplayModel[] = [];
  @ViewChild('f') testNgForm: NgForm;
  @ViewChild(MatTable, { static: true }) addedTableQuestions: MatTable<any>;
  id:number;

  constructor(public testService: TestService ,private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.questionsToAdd = this.route.snapshot.data.questions;

    this.id = this.route.snapshot.params['id'];
    this.testService.fetchTestToEdit(this.id);

    this.testForm = new FormGroup({
      'name': new FormControl(this.testService.getSelectedTest().name, Validators.required)
    });
    
    this.model = new TestAddModel("",[]);
    this.model.name = this.testService.getSelectedTest().name;

    this.questionsAdded = this.testService.getSelectedTest().questionDisplayDto;

    this.testService.getSelectedTest().questionDisplayDto.forEach(q=>
      {
        this.model.questionsIds.push(q.id);
      });
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

  clearForm() {

    this.testNgForm.resetForm();

    this.model = new TestAddModel("", []);
    this.questionsAdded = [];
    this.addedTableQuestions.renderRows();
  }

  numberOfQuestionsValidator(): boolean {
    return this.model.questionsIds.length <= 0;
  }

  canAddQuestion(id: number): boolean {
    return this.model.questionsIds.includes(id);
  }

  addQuestionIdToModel(id: number) {
    this.model.questionsIds.push(id);
    let question = this.questionsToAdd.find(q => q.id === id);
    this.questionsAdded.push(question);
    this.addedTableQuestions.renderRows();
  }

  onSubmit() {
    this.model.name = this.testForm.value.name;
    this.testService.updateTest(this.model, this.id).subscribe();

    this.clearForm();
    this.router.navigate(['test']);
  }

}
