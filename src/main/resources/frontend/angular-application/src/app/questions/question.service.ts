import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QuestionDisplayModel } from './question.models';
import { Observable } from 'rxjs';


@Injectable({providedIn: 'root' })
export class QuestionService{
    
    private selectedQuestion: QuestionDisplayModel = null;

    constructor(private http:HttpClient){};
    
    getQuestions(): Observable<QuestionDisplayModel[]>{
        return this.http.get<QuestionDisplayModel[]>('api/question');
    }

    setSelectedQuestion(q:QuestionDisplayModel)
    {
        this.selectedQuestion=q;
    }

    getSelectedQuestion() : QuestionDisplayModel
    {
        return this.selectedQuestion;
    }
    
}