import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { LoginModel } from './login.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  error = false;
  credentials:LoginModel;

  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required)
    })  
  }

  login(){
  
    this.credentials = new LoginModel(
      this.loginForm.value.username,
      this.loginForm.value.password
    );

    this.authService.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
    });
    this.error = true;
    return false;
  }

}
