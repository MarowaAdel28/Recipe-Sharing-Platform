import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private _http:HttpClient) { }

  getAll(url:string){
    return this._http.get<any[]>(`http://localhost:8080/${url}`)
  }
  getTop3(url:string){
    return this._http.get<any[]>(`http://localhost:8080/${url}/top3`)
  }
  getById(url:string,id:number){
    return this._http.get<any>(`http://localhost:8080/${url}/${id}`)
  }

  post(url:string,body:any,headers?:any){
    return this._http.post<any>(`http://localhost:8080/${url}`,body,{headers})
  }
  put(url:string,body:any,headers:any){
    return this._http.put<any>(`http://localhost:8080/${url}`,body,{headers})
  }
  delete(url:string,id:number){
    return this._http.delete<any>(`http://localhost:8080/${url}/${id}`)
  }
  softDelete(url:string,id:number,params: {isDeleted:boolean}){
    const httpParams = new HttpParams()
      .set('isDeleted', params.isDeleted);

    const options = { params: httpParams };
    return this._http.delete<any>(`http://localhost:8080/${url}/${id}`,options)
  }

  getPaginationRecipes(url: string, params: {page: string; size: string;}) {

  const httpParams = new HttpParams()
    .set('pageSize', params.size)
    .set('page', params.page);

  const options = { params: httpParams };

  return this._http.get<any[]>(`http://localhost:8080/${url}`,options);
  }

  getPaginationUserRecipes(url: string,id:number, params: {page: string; size: string;}) {

    const httpParams = new HttpParams()
      .set('pageSize', params.size)
      .set('page', params.page);

    const options = { params: httpParams };

    return this._http.get<any[]>(`http://localhost:8080/${url}/${id}`,options);
  }

  findRecipesByNameAndCategory(url: string, params: {name: string; categoryId: string; page: string; size: string;}) {

  const httpParams = new HttpParams()
    .set('name', params.name)
    .set('categoryId', params.categoryId)
    .set('pageSize', params.size)
    .set('page', params.page);

  const options = { params: httpParams };

  return this._http.get<any[]>(`http://localhost:8080/${url}`,options);
  }
}
