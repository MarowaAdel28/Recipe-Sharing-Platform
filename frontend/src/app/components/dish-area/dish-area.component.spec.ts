import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DishAreaComponent } from './dish-area.component';

describe('DishAreaComponent', () => {
  let component: DishAreaComponent;
  let fixture: ComponentFixture<DishAreaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DishAreaComponent]
    });
    fixture = TestBed.createComponent(DishAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
