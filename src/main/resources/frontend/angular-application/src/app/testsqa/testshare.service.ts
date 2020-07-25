import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestShareDisplayModel } from './test.models';

@Injectable({providedIn:'root'})
export class TestShareService{
    
    constructor(private http: HttpClient) { };

    fetchTestsShare(): Observable<TestShareDisplayModel[]>{
        return this.http.get<TestShareDisplayModel[]>('api/testshare');
    }
}