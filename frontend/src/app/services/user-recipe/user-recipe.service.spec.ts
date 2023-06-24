import { TestBed } from '@angular/core/testing';

import { UserRecipeService } from './user-recipe.service';

describe('UserRecipeService', () => {
  let service: UserRecipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserRecipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
