import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { RegisterModel } from './reqister.model';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  registerModel: RegisterModel;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {

    this.registerForm = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.email, Validators.required]),
      'password': new FormControl(null, Validators.required),
      'passwordconf': new FormControl(null, Validators.required)
    }, [this.passwordMathValidator])
  }
  
  register(){
      this.registerModel = new RegisterModel(
        this.registerForm.value.name,
        this.registerForm.value.email,
        this.registerForm.value.password
      );
  
      this.authService.register(this.registerModel).subscribe();
      
      this.router.navigateByUrl('/login');
  }

  passwordMathValidator(control: AbstractControl): { [key: string]: boolean } | null {
    
    const password = control.get('password').value;
    const passwordConfirmation = control.get('passwordconf').value;

    if(password !== passwordConfirmation){
      return {'password': false};
    }

    return null;
  }
}
