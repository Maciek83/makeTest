import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../question.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  constructor(public questionService:QuestionService, private router: Router) { }

  ngOnInit(): void {
  }

  navigateToEditPage(id: number)
  {
    this.router.navigate(['/editquestion/'+id]);
  }

}
