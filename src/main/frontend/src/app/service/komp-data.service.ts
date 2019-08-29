import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Kompanija} from "../classes/kompanija";

@Injectable({
  providedIn: 'root'
})
export class KompDataService {

  private kompanijaUrl: string;

  constructor(private http: HttpClient) {
    this.kompanijaUrl = 'http://localhost:8080/api/kompanije';
  }


  public findAll(): Observable<Kompanija[]> {
    return this.http.get<Kompanija[]>(this.kompanijaUrl);
  }

  public save(kompanija: Kompanija) {
    return this.http.post<Kompanija>(this.kompanijaUrl, kompanija);
  }
}
