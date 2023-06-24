import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditingRecipeComponent } from './editing-recipe.component';

describe('EditingRecipeComponent', () => {
  let component: EditingRecipeComponent;
  let fixture: ComponentFixture<EditingRecipeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditingRecipeComponent]
    });
    fixture = TestBed.createComponent(EditingRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
