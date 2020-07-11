import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { TestDisplayModel, TestAddModel } from './test.models';
import { Observable } from 'rxjs';

@Injectable({providedIn:'root'})
export class TestService {

    private selectedTest: TestDisplayModel = null;

    constructor(private http: HttpClient) { };

    fetchTests(): Observable<TestDisplayModel[]>{
        return this.http.get<TestDisplayModel[]>('api/test');
    }

    fetchTestToEdit(id: number) {
        this.http.get<TestDisplayModel>('api/test/' + id)
        .subscribe(data => this.selectedTest = data);
    }

    addTest(addTestModel: TestAddModel) : Observable<TestDisplayModel>{
        return this.http.post<TestDisplayModel>('api/test', addTestModel);
    }

    updateTest(addTestModel: TestAddModel, id:number): Observable<TestDisplayModel>{
        return this.http.patch<TestDisplayModel>('api/test/'+id, addTestModel);
    }

    setSelectedTestToNull(){
        this.selectedTest = null;
    }

    setSelectedTest(test: TestDisplayModel){
        this.selectedTest = test;
    }

    getSelectedTest(): TestDisplayModel{
        return this.selectedTest;
    }
}