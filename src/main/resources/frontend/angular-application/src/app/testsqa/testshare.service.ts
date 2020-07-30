import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestShareDisplayModel, TestDisplaySolveModel, ShareTestModel } from './test.models';
import { ActivatedRoute } from '@angular/router';

@Injectable({providedIn:'root'})
export class TestShareService {


    constructor(private http: HttpClient, private route: ActivatedRoute) { }


    fetchTestsShare(): Observable<TestShareDisplayModel[]>{
        return this.http.get<TestShareDisplayModel[]>('api/testshare');
    }

    shareTest(model: ShareTestModel): Observable<TestDisplaySolveModel>{
        return this.http.post<TestDisplaySolveModel>('api/testshare', model);
    }

    getTestToSolve(id: string, secret: string): Observable<TestDisplaySolveModel>{
        
        let params = new HttpParams();
        params = params.append('secret', secret);
        params = params.append('id', id);

        return this.http.get<TestDisplaySolveModel>('api/testsharesolve',
        {
            params: params
        });
    }
}