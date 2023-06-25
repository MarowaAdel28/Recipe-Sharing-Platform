import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouriteRecipeComponent } from './favourite-recipe.component';

describe('FavouriteRecipeComponent', () => {
  let component: FavouriteRecipeComponent;
  let fixture: ComponentFixture<FavouriteRecipeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FavouriteRecipeComponent]
    });
    fixture = TestBed.createComponent(FavouriteRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
