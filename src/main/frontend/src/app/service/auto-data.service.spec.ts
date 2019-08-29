import {TestBed} from '@angular/core/testing';

import {AutoDataService} from './auto-data.service';

describe('AutoDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AutoDataService = TestBed.get(AutoDataService);
    expect(service).toBeTruthy();
  });
});
