import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QuestionDisplayModel, QuestionCreateModel, QuestionEditModel } from './question.models';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class QuestionService  {
    private selectedQuestion: QuestionDisplayModel = null;

    constructor(private http: HttpClient) { }

    fetchQuestions(): Observable<QuestionDisplayModel[]> {
        return this.http.get<QuestionDisplayModel[]>('api/question');
    }

    fetchQuestionToEdit(id: number) {
        this.http.get<QuestionDisplayModel>('api/question/' + id)
            .subscribe(data => this.selectedQuestion = data);
    }

    editQuestion(id:number, questionEditModel: QuestionEditModel): Observable<QuestionDisplayModel> {
        return this.http.patch<QuestionDisplayModel>('api/question/'+id, questionEditModel);
    }

    addQuestion(questionCreateModel: QuestionCreateModel): Observable<QuestionDisplayModel> {
        return this.http.post<QuestionDisplayModel>('api/question', questionCreateModel);
    }

    setSelectedQuestion(questions:QuestionDisplayModel[], id: number) {
        let element = questions.find(q => q.id === id);
        this.selectedQuestion = element;
    }

    removeSelectedQuestion() {
        this.selectedQuestion = null;
    }

    getSelectedQuestion(): QuestionDisplayModel {
        return this.selectedQuestion;
    }

}