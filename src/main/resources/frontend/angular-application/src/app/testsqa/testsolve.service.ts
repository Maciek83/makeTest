import { Injectable } from '@angular/core';
import { TestSoveModel } from './test.models';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class TestSolveService{

    constructor(private http: HttpClient) { };

    solveTest(model: TestSoveModel): Observable<TestSoveModel>{
        return this.http.post<TestSoveModel>('api/testsolved', model);
    }

}