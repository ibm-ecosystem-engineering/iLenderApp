import { TestBed } from '@angular/core/testing';

import { WealthmanagerService } from './wealthmanager.service';

describe('WealthmanagerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WealthmanagerService = TestBed.get(WealthmanagerService);
    expect(service).toBeTruthy();
  });
});
