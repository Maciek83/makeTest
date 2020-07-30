import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router'
import { QuestionsComponent } from './questions/questions.component';
import { HomeComponent } from './home/home.component';
import { AddquestionComponent } from './questions/addquestion/addquestion.component';
import { EditquestionComponent } from './questions/editquestion/editquestion.component';
import { TestsqaComponent } from './testsqa/testsqa.component';
import { AddtestqaComponent } from './testsqa/addtestqa/addtestqa.component';
import { QuestionsResolver } from './questions/questions.resolver';
import { EdittestqaComponent } from './testsqa/edittestqa/edittestqa.component';
import { TestResolver } from './testsqa/test.resolver';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './services/auth.guard';
import { TestShareResolver } from './testsqa/testshare.resolver';
import { TestqashareComponent } from './testsqa/testqashare/testqashare.component';
import { TestqadisplaytosolveComponent } from './testsqa/testqadisplaytosolve/testqadisplaytosolve.component';
import { TestSolveResolver } from './testsqa/testsolve.resolver';
import { TestSolveGetAll } from './testsqa/testsolvegetall.resolver';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component:HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'testsolve', component: TestqadisplaytosolveComponent, resolve:{testSolve: TestSolveResolver}},
  {path: 'testshare', component: TestqashareComponent, resolve:{testsShare: TestShareResolver, testsSolved: TestSolveGetAll}, canActivate:[AuthGuard]},
  {path: 'question', component:QuestionsComponent, resolve:{questions: QuestionsResolver}, canActivate:[AuthGuard]},
  {path: 'addquestion', component:AddquestionComponent, canActivate:[AuthGuard]},
  {path: 'editquestion/:id', component:EditquestionComponent, canActivate:[AuthGuard]},
  {path: 'test', component:TestsqaComponent, resolve:{tests: TestResolver, questions: QuestionsResolver}, canActivate:[AuthGuard]},
  {path: 'addtest', component:AddtestqaComponent, resolve:{questions: QuestionsResolver}, canActivate:[AuthGuard]},
  {path: 'edittest/:id', component:EdittestqaComponent, resolve:{questions: QuestionsResolver}, canActivate:[AuthGuard]}
]


@NgModule({
  imports: [CommonModule, [RouterModule.forRoot(appRoutes)]],
  exports: [RouterModule]
})
export class AppRoutingModule { }
