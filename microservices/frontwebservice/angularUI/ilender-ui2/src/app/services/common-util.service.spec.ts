import { TestBed } from '@angular/core/testing';

import { CommonUtilService } from './common-util.service';

describe('CommonUtilService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommonUtilService = TestBed.get(CommonUtilService);
    expect(service).toBeTruthy();
  });
});
