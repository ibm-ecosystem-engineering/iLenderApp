import { TestBed } from '@angular/core/testing';

import { BusinessmanagerService } from './businessmanager.service';

describe('BusinessmanagerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BusinessmanagerService = TestBed.get(BusinessmanagerService);
    expect(service).toBeTruthy();
  });
});
