import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./data/user";
import {Profile} from "./data/profile";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  API_URL = 'http://localhost:8080/users';
  API_PUBLIC_URL = 'http://localhost:8080/public';

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

  create(user: { identification: string, secret: string, name: string, born: Date, conta: string }): Observable<User> {
    return this.http.post<User>(`${this.API_PUBLIC_URL}/users`, user);
  }

  update(userId: string, user: User): Observable<User> {
    return this.http.put<User>(`${this.API_URL}/${userId}`, user);
  }

  all(): Observable<User[]> {
    return this.http.get<User[]>(`${this.API_URL}`);
  }


}
