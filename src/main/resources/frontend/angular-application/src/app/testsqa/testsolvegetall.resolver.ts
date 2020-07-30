import { TestSolveService } from './testsolve.service';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TestSoveInfoModel } from './test.models';
import { Injectable } from '@angular/core';

@Injectable({providedIn:'root'})
export class TestSolveGetAll implements Resolve<TestSoveInfoModel[]>{
    constructor(private testSolveService: TestSolveService){}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): TestSoveInfoModel[] | import("rxjs").Observable<TestSoveInfoModel[]> | Promise<TestSoveInfoModel[]> {
        return this.testSolveService.getSolvedTests();
    }
}