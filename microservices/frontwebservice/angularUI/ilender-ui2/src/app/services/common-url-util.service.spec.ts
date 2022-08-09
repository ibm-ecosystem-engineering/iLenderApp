import { TestBed } from '@angular/core/testing';

import { CommonUrlUtilService } from './common-url-util.service';

describe('CommonUrlUtilService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommonUrlUtilService = TestBed.get(CommonUrlUtilService);
    expect(service).toBeTruthy();
  });
});
