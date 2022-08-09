import { TestBed } from '@angular/core/testing';

import { WcLocalStorageService } from './wc-local-storage.service';

describe('WcLocalStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WcLocalStorageService = TestBed.get(WcLocalStorageService);
    expect(service).toBeTruthy();
  });
});
