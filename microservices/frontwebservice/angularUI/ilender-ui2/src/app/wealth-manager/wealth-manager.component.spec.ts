import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WealthManagerComponent } from './wealth-manager.component';

describe('WealthManagerComponent', () => {
  let component: WealthManagerComponent;
  let fixture: ComponentFixture<WealthManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WealthManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WealthManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
