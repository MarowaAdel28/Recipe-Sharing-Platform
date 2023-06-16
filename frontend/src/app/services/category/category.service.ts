import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";
import {CategoryModel} from "../../models/category-model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _apiService:ApiService) { }

  getAll(){
    return this._apiService.getAll("category")
  }
  getTop3(){
    return this._apiService.getTop3("category")
  }
  getById(id:number){
    return this._apiService.getById("category",id)
  }
  post(categoryModel:CategoryModel){
    return this._apiService.post("category",categoryModel)
  }
  update(categoryModel:CategoryModel){
    return this._apiService.put("category",categoryModel)
  }
  delete(id:number){
    return this._apiService.delete("category",id)
  }
}
