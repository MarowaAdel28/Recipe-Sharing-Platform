import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchiveRecipesComponent } from './archive-recipes.component';

describe('ArchiveRecipesComponent', () => {
  let component: ArchiveRecipesComponent;
  let fixture: ComponentFixture<ArchiveRecipesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArchiveRecipesComponent]
    });
    fixture = TestBed.createComponent(ArchiveRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
