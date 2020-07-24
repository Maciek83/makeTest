import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TestShareDisplayModel } from './test.models';
import { TestService } from './test.service';

@Injectable({providedIn:'root'})
export class TestShareResolver implements Resolve<TestShareDisplayModel[]>{

    constructor(private testService: TestService){}
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): TestShareDisplayModel[] | import("rxjs").Observable<TestShareDisplayModel[]> | Promise<TestShareDisplayModel[]> {
        return this.testService.fetchTestsShare();
    }

    

}