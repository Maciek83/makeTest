import { Component, OnInit } from '@angular/core';
import { QuestionService } from './question.service';
import { ActivatedRoute } from '@angular/router';
import { QuestionDisplayModel } from './question.models';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'content', 'select'];
  questions: QuestionDisplayModel[];
  constructor(public questionService: QuestionService, private route: ActivatedRoute) { }
  
  ngOnInit(): void {
    this.questions = this.route.snapshot.data.questions;
    this.questionService.removeSelectedQuestion();
  }

  setSelectedQuestion(id:number)
  {
    this.questionService.setSelectedQuestion(this.questions, id);
  }

}
