import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private _http:HttpClient) { }

  getAll(url:string){
    return this._http.get<any[]>(`http://localhost:8060/${url}`)
  }
  getTop3(url:string){
    return this._http.get<any[]>(`http://localhost:8060/${url}/top3`)
  }
  getById(url:string,id:number){
    return this._http.get<any>(`http://localhost:8060/${url}/${id}`)
  }

  post(url:string,body:any){
    return this._http.post<any>(`http://localhost:8060/${url}`,body)
  }
  put(url:string,body:any){
    return this._http.put<any>(`http://localhost:8060/${url}`,body)
  }
  delete(url:string,id:number){
    return this._http.delete<any>(`http://localhost:8060/${url}/${id}`)
  }

  getPaginationRecipes(url: string, params: {page: string; size: string;}) {

  const httpParams = new HttpParams()
    .set('pageSize', params.size)
    .set('page', params.page);

  const options = { params: httpParams };

  return this._http.get<any[]>(`http://localhost:8060/${url}`,options);
  }

}
