import { Component, OnInit } from '@angular/core';
import { TestService } from '../test.service';
import { Router } from '@angular/router';
import { QuestionService } from 'src/app/questions/question.service';

@Component({
  selector: 'app-testqa',
  templateUrl: './testqa.component.html',
  styleUrls: ['./testqa.component.css']
})
export class TestqaComponent implements OnInit {

  constructor(public testService: TestService, private questionService: QuestionService, private router: Router) {}

  ngOnInit(): void {
  }

  navigateToEditGuestion(id: number)
  {
    this.questionService.setSelectedQuestion(id);
    this.router.navigate(['/editquestion/'+id]);
  }

}
