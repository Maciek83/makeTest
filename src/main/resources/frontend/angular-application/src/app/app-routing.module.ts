import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router'
import { QuestionsComponent } from './questions/questions.component';
import { HomeComponent } from './home/home.component';
import { AddquestionComponent } from './questions/addquestion/addquestion.component';
import { EditquestionComponent } from './questions/editquestion/editquestion.component';
import { TestsqaComponent } from './testsqa/testsqa.component';
import { AddtestqaComponent } from './testsqa/addtestqa/addtestqa.component';
import { QuestionsResolver } from './questions/question.resolver';
import { EdittestqaComponent } from './testsqa/edittestqa/edittestqa.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component:HomeComponent},
  {path: 'question', component:QuestionsComponent, resolve:{questions: QuestionsResolver}},
  {path: 'addquestion', component:AddquestionComponent},
  {path: 'editquestion/:id', component:EditquestionComponent},
  {path: 'test', component:TestsqaComponent, resolve:{questions: QuestionsResolver}},
  {path: 'addtest', component:AddtestqaComponent, resolve:{questions: QuestionsResolver}},
  {path: 'edittest/:id', component:EdittestqaComponent}
]


@NgModule({
  imports: [CommonModule, [RouterModule.forRoot(appRoutes)]],
  exports: [RouterModule]
})
export class AppRoutingModule { }
