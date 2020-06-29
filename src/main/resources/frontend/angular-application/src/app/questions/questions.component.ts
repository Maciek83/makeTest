import { Component, OnInit } from '@angular/core';
import { QuestionService } from './question.service';
import { QuestionDisplayModel } from './question.models';
import { throwError } from 'rxjs/internal/observable/throwError';
import { retry, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'content', 'select'];
  public questions: QuestionDisplayModel[];

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.questionService.getQuestions().subscribe(data=>this.questions=data);
  }

  setSelectedQuestion(id:number)
  {
      let element = this.questions.find(q=>q.id===id);
      this.questionService.setSelectedQuestion(element);
  }

}
