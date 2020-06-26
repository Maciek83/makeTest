import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router'
import { QuestionsComponent } from './questions/questions.component';
import { HomeComponent } from './home/home.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component:HomeComponent},
  {path: 'question', component:QuestionsComponent}
]


@NgModule({
  imports: [CommonModule, [RouterModule.forRoot(appRoutes)]],
  exports: [RouterModule]
})
export class AppRoutingModule { }
