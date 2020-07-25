import { Component, OnInit } from '@angular/core';
import { TestShareDisplayModel } from '../test.models';
import { TestService } from '../test.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-testqashare',
  templateUrl: './testqashare.component.html',
  styleUrls: ['./testqashare.component.css']
})
export class TestqashareComponent implements OnInit {

  displayedColumnsShared: string[] = ['name','url'];
  testsShare: TestShareDisplayModel[];

  constructor(public testService: TestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.testsShare = this.route.snapshot.data.testsShare;
  }

}
