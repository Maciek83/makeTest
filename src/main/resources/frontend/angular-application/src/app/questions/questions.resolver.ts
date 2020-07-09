import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { QuestionDisplayModel } from './question.models';
import { QuestionService } from './question.service';

@Injectable({providedIn:'root'})
export class QuestionsResolver implements Resolve<QuestionDisplayModel[]>{

    constructor(private questionService: QuestionService){}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): QuestionDisplayModel[] | import("rxjs").Observable<QuestionDisplayModel[]> | Promise<QuestionDisplayModel[]> {
        return this.questionService.fetchQuestions();
    }

}