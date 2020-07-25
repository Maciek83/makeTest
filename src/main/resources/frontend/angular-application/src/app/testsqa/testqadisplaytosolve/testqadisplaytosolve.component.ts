import { Component, OnInit } from '@angular/core';
import { TestShareService } from '../testshare.service';
import { ActivatedRoute } from '@angular/router';
import { TestDisplaySolveModel } from '../test.models';

@Component({
  selector: 'app-testqadisplaytosolve',
  templateUrl: './testqadisplaytosolve.component.html',
  styleUrls: ['./testqadisplaytosolve.component.css']
})
export class TestqadisplaytosolveComponent implements OnInit {

  id:string;
  secret:string;
  displayModel:TestDisplaySolveModel;

  constructor(private testShareService:TestShareService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.id = params['id'];
        this.secret = params['secret'];
    });

    this.testShareService.getTestToSolve(this.id, this.secret).subscribe(
      data => this.displayModel = data
    );
  }



}
