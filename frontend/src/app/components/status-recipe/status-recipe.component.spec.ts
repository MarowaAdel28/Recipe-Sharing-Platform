import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusRecipeComponent } from './status-recipe.component';

describe('StatusRecipeComponent', () => {
  let component: StatusRecipeComponent;
  let fixture: ComponentFixture<StatusRecipeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatusRecipeComponent]
    });
    fixture = TestBed.createComponent(StatusRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
