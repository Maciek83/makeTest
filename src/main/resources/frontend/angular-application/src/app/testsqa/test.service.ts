import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { TestDisplayModel, TestAddModel } from './test.models';
import { Observable } from 'rxjs';

@Injectable({providedIn:'root'})
export class TestService {

    private tests: TestDisplayModel[];
    private selectedTest: TestDisplayModel = null;

    constructor(private http: HttpClient) { };

    fetchTests(): TestDisplayModel[]{
        this.http.get<TestDisplayModel[]>('api/test')
            .subscribe(data => this.tests = data);
        return this.tests;
    }

    addTest(addTestModel: TestAddModel) : Observable<TestDisplayModel>{
        return this.http.post<TestDisplayModel>('api/test', addTestModel);
    }

    setSelectedTestToNull(){
        this.selectedTest = null;
    }

    setSelectedTest(id:number){
        let item = this.getTests().find(t=>t.id==id);
        this.selectedTest = item;
    }

    getSelectedTest(): TestDisplayModel{
        return this.selectedTest;
    }

    getTests(): TestDisplayModel[]{
        return this.tests;
    }
}