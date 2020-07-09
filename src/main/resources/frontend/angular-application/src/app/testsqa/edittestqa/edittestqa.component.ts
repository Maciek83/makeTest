import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TestService } from '../test.service';

@Component({
  selector: 'app-edittestqa',
  templateUrl: './edittestqa.component.html',
  styleUrls: ['./edittestqa.component.css']
})
export class EdittestqaComponent implements OnInit {

  id:number;

  constructor(public testService: TestService ,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.testService.fetchTestToEdit(this.id);
    console.log(this.testService.getSelectedTest());
  }

}
