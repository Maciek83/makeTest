import { Injectable } from '@angular/core';
import { TestSoveModel, TestSoveInfoModel } from './test.models';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class TestSolveService{

    constructor(private http: HttpClient) { };

    solveTest(model: TestSoveModel): Observable<TestSoveInfoModel>{
        return this.http.post<TestSoveInfoModel>('api/testsolved', model);
    }

    getSolvedTests() : Observable<TestSoveInfoModel[]>{
        return this.http.get<TestSoveInfoModel[]>('api/testsolved');
    }

}