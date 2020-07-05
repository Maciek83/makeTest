import { Component, OnInit, OnDestroy } from '@angular/core';
import { QuestionService } from './question.service';
import { QuestionDisplayModel } from './question.models';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'content', 'select'];

  constructor(public questionService: QuestionService) { }

  ngOnInit(): void {
    this.questionService.fetchQuestions();
  }

  setSelectedQuestion(id:number)
  {
    this.questionService.setSelectedQuestion(id);
  }

}
