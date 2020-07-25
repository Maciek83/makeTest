import { Component, OnInit } from '@angular/core';
import { TestService } from './test.service';
import { TestDisplayModel } from './test.models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-testsqa',
  templateUrl: './testsqa.component.html',
  styleUrls: ['./testsqa.component.css']
})
export class TestsqaComponent implements OnInit {

  tests: TestDisplayModel[];
  displayedColumns: string[] = ['id', 'name', 'select'];
  
  constructor(public testService: TestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.tests = this.route.snapshot.data.tests;
    this.testService.setSelectedTestToNull();
  }

  setSelectedTest(id:number){
    this.testService.setSelectedTest(this.tests.find(t=>t.id===id));
  }

}
