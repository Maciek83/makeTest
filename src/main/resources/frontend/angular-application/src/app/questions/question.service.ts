import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QuestionDisplayModel, QuestionCreateModel } from './question.models';
import { Observable } from 'rxjs';


@Injectable({providedIn: 'root' })
export class QuestionService{
    
    private questions: QuestionDisplayModel[]
    private selectedQuestion: QuestionDisplayModel = null;

    constructor(private http:HttpClient){};
    
    fetchQuestions(): QuestionDisplayModel[]{
        this.http.get<QuestionDisplayModel[]>('api/question')
        .subscribe(data => this.questions = data);
        return this.questions;
    }

    fetchQuestionToEdit(id: number){
        this.http.get<QuestionDisplayModel>('api/question/'+id)
        .subscribe(data => this.selectedQuestion = data);
    }

    addQuestion(questionCreateModel: QuestionCreateModel) : Observable<QuestionDisplayModel>
    {
        return this.http.post<QuestionDisplayModel>('api/question', questionCreateModel);
    }

    setSelectedQuestion(id: number)
    {
        let element = this.getQuestions().find(q=>q.id===id);
        this.selectedQuestion= element;
    }

    deleteQuestion(id: number){
        this.questions.splice(id,1);
    }

    removeSelectedQuestion(){
        this.selectedQuestion = null;
    }

    getSelectedQuestion() : QuestionDisplayModel
    {
        return this.selectedQuestion;
    }

    getQuestions(){
        return this.questions;
    }
    
}