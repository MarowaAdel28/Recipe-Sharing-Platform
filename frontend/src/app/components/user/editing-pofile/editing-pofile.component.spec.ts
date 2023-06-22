import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditingPofileComponent } from './editing-pofile.component';

describe('EditingPofileComponent', () => {
  let component: EditingPofileComponent;
  let fixture: ComponentFixture<EditingPofileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditingPofileComponent]
    });
    fixture = TestBed.createComponent(EditingPofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
