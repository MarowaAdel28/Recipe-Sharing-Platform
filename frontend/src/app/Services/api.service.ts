import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseDashboardUrl = 'http://127.0.0.1:8080/dashboard/'
  private baseAdminRecipeUrl = 'http://127.0.0.1:8080/adminRecipe/'


  constructor(private http: HttpClient) { }

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
      .get(url, httpOptions)
      .toPromise()
      .then((data) => {
        return data;
      });
  }


  barChartDataAge() {
    const url = `${this.baseDashboardUrl + "getAgeStat"}`;
    return this.http
      .get(url, httpOptions)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

  lineChartData() {
    const url = `${this.baseDashboardUrl + "getRegisterDateStat"}`;
    return this.http
      .get(url, httpOptions)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

  pieChartData() {
    const url = `${this.baseDashboardUrl + "getRecipeStatusStat"}`;
    return this.http
      .get(url, httpOptions)
      .toPromise()
      .then((data) => {
        return data;
      });
  }


  getUsersData(page: number) {
    const url = `${this.baseDashboardUrl + "getUsersStat/" + page}`;
    return this.http
      .get(url, httpOptions)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

  getUsersCount() {
    const url = `${this.baseDashboardUrl + "getUsersCount"}`;
    return this.http
      .get(url, httpOptions)
      .toPromise()
      .then((data) => {
        return data;
      });
  }

}
