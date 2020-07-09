import { Injectable } from '@angular/core';
import { TestService } from './test.service';
import { TestDisplayModel } from './test.models';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({providedIn:'root'})
export class TestResolver implements Resolve<TestDisplayModel[]>{
    
    constructor(private testService: TestService){}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): TestDisplayModel[] | import("rxjs").Observable<TestDisplayModel[]> | Promise<TestDisplayModel[]> {
        return this.testService.fetchTests();
    }


}