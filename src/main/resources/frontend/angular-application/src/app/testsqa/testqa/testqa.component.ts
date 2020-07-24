import { Component, OnInit } from '@angular/core';
import { TestService } from '../test.service';
import { Router, ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/questions/question.service';
import { QuestionDisplayModel } from 'src/app/questions/question.models';
import { ShareTestModel } from '../test.models';

@Component({
  selector: 'app-testqa',
  templateUrl: './testqa.component.html',
  styleUrls: ['./testqa.component.css']
})
export class TestqaComponent implements OnInit {

  questions: QuestionDisplayModel[];
  constructor(public testService: TestService, private questionService: QuestionService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.questions = this.route.snapshot.data.questions;
  }

  navigateToEditQuestion(id: number)
  {
    this.questionService.setSelectedQuestion(this.questions, id);
    this.router.navigate(['/editquestion/'+id]);
  }

  navigateToEditTest(id: number){
    this.router.navigate(['/edittest/'+id]);
  }

  share(id: number){
    const model = new ShareTestModel(id);
    this.testService.shareTest(model).subscribe();
  }

}