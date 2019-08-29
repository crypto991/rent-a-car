import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Automobil} from "../classes/automobil";


@Injectable({
  providedIn: 'root'
})
export class AutoDataService {

  private automobilUrl: string;

  automobil: Automobil;

  private urlPage: string;


  constructor(private http: HttpClient) {
    this.automobilUrl = 'http://localhost:8080/api/automobili';
    this.urlPage = 'http://localhost:8080/api/automobili?pageNum=';
  }

  public getPageClient(page:number): Observable<any>{
    var url = this.urlPage;
    url=url+page;
    return this.http.get<any>(url,  {observe: 'response'});
  }


  public findAll(): Observable<any> {
    return this.http.get<Automobil[]>(this.automobilUrl,{observe: 'response'});
  }



  public save(automobil: Automobil) {
    return this.http.post<Automobil>('http://localhost:8080/api/automobili', automobil);
  }



  public find(model, godiste, potrosnja, pageNum): Observable<any> {

    return this.http.get<any>(this.automobilUrl, {params : {'model':model, 'godiste':godiste,
      'potrosnja': potrosnja, 'pageNum': pageNum}, observe: "response"});
  }

  public update(id,automobil: Automobil) {
    return this.http.put<Automobil>(`http://localhost:8080/api/automobili/${id}`, automobil);
  }

  public delete(id) {
    return this.http.delete<Automobil>(`http://localhost:8080/api/automobili/${id}`);
  }

  public getById(id) {
    return this.http.get<Automobil>(`http://localhost:8080/api/automobili/${id}`);
  }

  public iznajmi(id){
    return this.http.post(`http://localhost:8080/api/automobili/${id}/iznajmljivanje`, null);
  }


}
