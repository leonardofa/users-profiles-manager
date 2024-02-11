import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./data/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  API_URL = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {
  }

  login(credentials: { identification: string, secret: string }): Observable<User> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(`${credentials.identification}:${credentials.secret}`),
    });
    return this.http.get<User>(`${this.API_URL}/find/identification/${credentials.identification}`, {headers});
  }

  get(userId: string): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/${userId}`);
  }

}
