import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenuComponent } from './menu/menu.component';
import { AppRoutingModule } from './app-routing.module';
import { AppMateriaModule } from './app-materia.module';
import { QuestionsComponent } from './questions/questions.component';
import { QuestionComponent } from './questions/question/question.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { EditquestionComponent } from './questions/editquestion/editquestion.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AddquestionComponent } from './questions/addquestion/addquestion.component';
import { HttpInterceptorClass } from './interceptor/http.interceptor';
import { TestsqaComponent } from './testsqa/testsqa.component';
import { TestqaComponent } from './testsqa/testqa/testqa.component';
import { AddtestqaComponent } from './testsqa/addtestqa/addtestqa.component';
import { EdittestqaComponent } from './testsqa/edittestqa/edittestqa.component';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    QuestionsComponent,
    QuestionComponent,
    HomeComponent,
    EditquestionComponent,
    AddquestionComponent,
    TestsqaComponent,
    TestqaComponent,
    AddtestqaComponent,
    EdittestqaComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppMateriaModule,
    AppRoutingModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorClass,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
