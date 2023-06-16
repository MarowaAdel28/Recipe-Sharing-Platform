import { Injectable } from '@angular/core';
import {ApiService} from "../api.service";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _apiService:ApiService) { }

  getAll(){
    return this._apiService.getAll("category")
  }
}
