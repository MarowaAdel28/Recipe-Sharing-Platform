import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };



@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }


  private baseDashboardUrl = 'http://127.0.0.1:8080/dashboard/'
  private baseAdminRecipeUrl = 'http://127.0.0.1:8080/adminRecipe/'



  rejectRecipe(recipeId:number, message:string) {
    return this.http.patch(`${this.baseAdminRecipeUrl+"rejectRecipe?recipeId="+recipeId+"&message="+message}`, {});

  }

  acceptRecipe(recipeId:number) {
    return this.http.patch(`${this.baseAdminRecipeUrl+ "acceptRecipe/"+ recipeId}`,{});
  }

  getِAcceptedRecipesData(page: number){
    return this.http.get(`${this.baseAdminRecipeUrl+ "getAcceptedRecipes/"+ page}`);
  }

  getAcceptedCount() {
    return this.http.get(`${this.baseAdminRecipeUrl+ "getAcceptedCount"}`);
  }

  getِRejectedRecipesData(page: number){
    return this.http.get(`${this.baseAdminRecipeUrl+ "getRejectedRecipes/"+ page}`);
  }

  getRejectedCount() {
    return this.http.get(`${this.baseAdminRecipeUrl+ "getRejectedCount"}`);
  }

  getِWaitingRecipesData(page: number){
    return this.http.get(`${this.baseAdminRecipeUrl+ "getWaitingRecipes/"+ page}`);
  }

  getWaitingCount() {
    return this.http.get(`${this.baseAdminRecipeUrl+ "getWaitingCount"}`);
  }



  barChartDataGender() {
    const url = `${this.baseDashboardUrl + "getGenderStat"}`;
    return this.http
      .get(url)
      .toPromise()
      .then((data) => {
        return data;
      });
  }


  barChartDataAge() {
    const url = `${this.baseDashboardUrl + "getAgeStat"}`;
    return this.http
      .get(url)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

  lineChartData() {
    const url = `${this.baseDashboardUrl + "getRegisterDateStat"}`;
    return this.http
      .get(url)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

  pieChartData() {
    const url = `${this.baseDashboardUrl + "getRecipeStatusStat"}`;
    return this.http
      .get(url)
      .toPromise()
      .then((data) => {
        return data;
      });
  }


  getUsersData(page: number) {
    const url = `${this.baseDashboardUrl + "getUsersStat/" + page}`;
    return this.http
      .get(url)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

  getUsersCount() {
    const url = `${this.baseDashboardUrl + "getUsersCount"}`;
    return this.http
      .get(url)
      .toPromise()
      .then((data) => {
        return data;
      });
  }



  getAll(url:string){
    return this.http.get<any[]>(`http://localhost:8080/${url}`)
  }
  getTop3(url:string){
    return this.http.get<any[]>(`http://localhost:8080/${url}/top3`)
  }
  getById(url:string,id:number){
    return this.http.get<any>(`http://localhost:8080/${url}/${id}`)
  }

  post(url:string,body:any,headers:any){
    return this.http.post<any>(`http://localhost:8080/${url}`,body,{headers})
  }
  put(url:string,body:any){
    return this.http.put<any>(`http://localhost:8080/${url}`,body)
  }

  put_header(url:string,body:any,headers:any){
    return this.http.put<any>(`http://localhost:8080/${url}`,body,{headers})
  }
  delete(url:string,id:number){
    return this.http.delete<any>(`http://localhost:8080/${url}/${id}`)
  }

  softDelete(url:string,id:number,params: {isDeleted:boolean}){
    const httpParams = new HttpParams()
      .set('isDeleted', params.isDeleted);

    const options = { params: httpParams };
    return this.http.delete<any>(`http://localhost:8080/${url}/${id}`,options)
  }

  getPaginationRecipes(url: string, params: {page: string; size: string;}) {

  const httpParams = new HttpParams()
    .set('pageSize', params.size)
    .set('page', params.page);

  const options = { params: httpParams };

  return this.http.get<any[]>(`http://localhost:8080/${url}`,options);
  }
  getPaginationUserRecipes(url: string,id:number, params: {page: string; size: string;}) {

    const httpParams = new HttpParams()
      .set('pageSize', params.size)
      .set('page', params.page);

    const options = { params: httpParams };

    return this.http.get<any[]>(`http://localhost:8080/${url}/${id}`,options);
  }

  findRecipesByNameAndCategory(url: string, params: {name: string; categoryId: string; page: string; size: string;}) {

    const httpParams = new HttpParams()
      .set('name', params.name)
      .set('categoryId', params.categoryId)
      .set('pageSize', params.size)
      .set('page', params.page);

    const options = { params: httpParams };

    return this.http.get<any[]>(`http://localhost:8080/${url}`,options);
  }

}
