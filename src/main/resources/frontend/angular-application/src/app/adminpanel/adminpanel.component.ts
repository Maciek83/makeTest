import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminpanel',
  templateUrl: './adminpanel.component.html',
  styleUrls: ['./adminpanel.component.css']
})
export class AdminpanelComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToCreateQuestions(){
    this.router.navigate(['/addquestion']);
  }

  goToViewQuestions(){
    this.router.navigate(['/question']);
  }

  goToAddTest(){
    this.router.navigate(['/addtest']);
  }

  goToViewShareTests(){
    this.router.navigate(['/test']);
  }

  goToSharedSolvedTests(){
    this.router.navigate(['/testshare']);
  }



}
