import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router'
import { QuestionsComponent } from './questions/questions.component';
import { HomeComponent } from './home/home.component';
import { AddquestionComponent } from './questions/addquestion/addquestion.component';
import { EditquestionComponent } from './questions/editquestion/editquestion.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component:HomeComponent},
  {path: 'question', component:QuestionsComponent},
  {path: 'addquestion', component:AddquestionComponent},
  {path: 'editquestion', component:EditquestionComponent}
]


@NgModule({
  imports: [CommonModule, [RouterModule.forRoot(appRoutes)]],
  exports: [RouterModule]
})
export class AppRoutingModule { }
