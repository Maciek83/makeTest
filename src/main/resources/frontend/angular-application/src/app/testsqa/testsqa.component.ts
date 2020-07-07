import { Component, OnInit } from '@angular/core';
import { TestService } from './test.service';

@Component({
  selector: 'app-testsqa',
  templateUrl: './testsqa.component.html',
  styleUrls: ['./testsqa.component.css']
})
export class TestsqaComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'select'];
  constructor(public testService: TestService) { }

  ngOnInit(): void {
    this.testService.fetchTests();
    this.testService.setSelectedTestToNull();
  }

  setSelectedTest(id:number){
    this.testService.setSelectedTest(id);
  }

}
