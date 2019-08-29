import {TestBed} from '@angular/core/testing';

import {KompDataService} from './komp-data.service';

describe('KompDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KompDataService = TestBed.get(KompDataService);
    expect(service).toBeTruthy();
  });
});
