import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TestShareDisplayModel } from './test.models';
import { TestShareService } from './testshare.service';

@Injectable({providedIn:'root'})
export class TestShareResolver implements Resolve<TestShareDisplayModel[]>{

    constructor(private testShareService: TestShareService){}
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): TestShareDisplayModel[] | import("rxjs").Observable<TestShareDisplayModel[]> | Promise<TestShareDisplayModel[]> {
        return this.testShareService.fetchTestsShare();
    }

    

}