import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: any = [];
  pageSize: number = 10;
  usersCount: number = 0;
  pageIndex: number = 0;


  constructor(private service: ApiService) {
  }


  ngOnInit(): void {
    this.getUsersCount();
    this.getUsers();

  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.getUsers();
  }


  getUsers() {
    this.service.getUsersData(this.pageIndex).then((res: any) => {
      this.users = res;
    });
  }

  getUsersCount() {
    this.service.getUsersCount().then((res: any) => {
      this.usersCount = res;
    });
  }
}
