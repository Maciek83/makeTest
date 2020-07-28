import { Injectable } from '@angular/core';
import { TestDisplaySolveModel } from './test.models';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TestShareService } from './testshare.service';

@Injectable({providedIn:'root'})
export class TestSolveResolver implements Resolve<TestDisplaySolveModel>{
    constructor(private testShareService: TestShareService){}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): TestDisplaySolveModel | import("rxjs").Observable<TestDisplaySolveModel> | Promise<TestDisplaySolveModel> {
        let id = route.queryParamMap.get('id');
        let secret = route.queryParamMap.get('secret');
        return this.testShareService.getTestToSolve(id, secret);
    }
}