import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFavoriteRecipesComponent } from './user-favorite-recipes.component';

describe('UserFavoriteRecipesComponent', () => {
  let component: UserFavoriteRecipesComponent;
  let fixture: ComponentFixture<UserFavoriteRecipesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserFavoriteRecipesComponent]
    });
    fixture = TestBed.createComponent(UserFavoriteRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
