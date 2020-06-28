import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenuComponent } from './menu/menu.component';
import { AppRoutingModule } from './app-routing.module';
import { AppMateriaModule } from './app-materia.module';
import { QuestionsComponent } from './questions/questions.component';
import { QuestionComponent } from './questions/question/question.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { EditquestionComponent } from './questions/editquestion/editquestion.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AddquestionComponent } from './questions/addquestion/addquestion.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    QuestionsComponent,
    QuestionComponent,
    HomeComponent,
    EditquestionComponent,
    AddquestionComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
