import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppRateComponent } from './app-rate.component';

describe('AppRateComponent', () => {
  let component: AppRateComponent;
  let fixture: ComponentFixture<AppRateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppRateComponent]
    });
    fixture = TestBed.createComponent(AppRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
