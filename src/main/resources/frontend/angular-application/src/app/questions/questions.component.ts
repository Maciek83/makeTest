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

  public questions: QuestionDisplayModel[];
  public errorMessage:string = '';

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.questionService.getQuestions()
    .pipe(
      retry(1),
      catchError(this.handleError))
    .subscribe(data=>this.questions=data);
  }

  handleError(error) {
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      this.errorMessage = error.error.message;
    } else {
      // Get server-side error
      this.errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(this.errorMessage);
 }

}
