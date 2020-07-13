import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
    public authenticated = false;
    constructor(private http: HttpClient, private router: Router) { }

    authenticate(credentials, callback) {
        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get('api/user', { headers: headers }).subscribe(response => {
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        })
    }

    logout() {
        this.http.post('logout', {}).pipe(finalize(() => {
            this.authenticated = false;
            this.router.navigateByUrl('/login');
        })).subscribe();
    }
}