import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private _http:HttpClient) { }

  getAll(url:string){
    return this._http.get<any[]>(`http://localhost:8080/${url}`)
  }
  getById(url:string,id:number){
    return this._http.get<any>(`http://localhost:8080/${url}/${id}`)
  }

  post(url:string,body:any,headers:any){
    return this._http.post<any>(`http://localhost:8080/${url}`,body,{headers})
  }
  put(url:string,body:any){
    return this._http.put<any>(`http://localhost:8080/${url}`,body)
  }
  delete(url:string,id:number){
    return this._http.delete<any>(`http://localhost:8080/${url}/${id}`)
  }
}
