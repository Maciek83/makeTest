import { Component, OnInit } from '@angular/core';
import { TestShareDisplayModel, TestSoveInfoModel } from '../test.models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-testqashare',
  templateUrl: './testqashare.component.html',
  styleUrls: ['./testqashare.component.css']
})
export class TestqashareComponent implements OnInit {

  displayedColumnsShared: string[] = ['name','url'];
  displayedColumnsSolved: string[] = ['name','username','points','maxpoints','passed','report'];
  testsShare: TestShareDisplayModel[];
  testsSolved: TestSoveInfoModel[];

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.testsShare = this.route.snapshot.data.testsShare;
    this.testsSolved = this.route.snapshot.data.testsSolved;
  }

}
