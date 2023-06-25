import { TestBed } from '@angular/core/testing';

import { FavoruiteService } from './favourite.service';

describe('FavoruiteService', () => {
  let service: FavoruiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavoruiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
