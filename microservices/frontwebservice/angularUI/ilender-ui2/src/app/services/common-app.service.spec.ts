import { TestBed } from '@angular/core/testing';

import { CommonAppService } from './common-app.service';

describe('CommonAppService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommonAppService = TestBed.get(CommonAppService);
    expect(service).toBeTruthy();
  });
});
