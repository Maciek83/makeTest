import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TestShareService } from '../testshare.service';
import { TestService } from '../test.service';
import { ShareTestModel } from '../test.models';

@Component({
  selector: 'app-testshareform',
  templateUrl: './testshareform.component.html',
  styleUrls: ['./testshareform.component.css']
})
export class TestshareformComponent implements OnInit {

  testShareForm: FormGroup;
  id: number;
  numberOfQuestions: number;
  model: ShareTestModel;

  constructor(private route: ActivatedRoute, private testShareService: TestShareService, public testService: TestService, private router: Router) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.params['id'];
    this.testService.fetchTestToEdit(this.id);

    this.numberOfQuestions = this.testService.getSelectedTest().questionDisplayDto.length;

    this.setupForm();
  }

  setupForm(){
    this.testShareForm = new FormGroup({
      'points': new FormControl(null, Validators.required),
      'maxpoints': new FormControl(this.numberOfQuestions, Validators.required)
    },[this.validPoints])
  }

  onSubmit(){
      this.model = new ShareTestModel(this.id, this.testShareForm.value.points);
      this.testShareService.shareTest(this.model).subscribe(
        (data) =>{
          this.router.navigate(['/testshare/']);
        }
      );
  }

  validPoints(control: AbstractControl): { [key: string]: boolean } | null {
    
    let points = control.get('points').value;
    let maxpoints = control.get('maxpoints').value;

    if(points < 0 || points > maxpoints || points == 0){
      return {'points': false};
    }
    return null;
  }

}
