import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalOverviewComponent } from './goal-overview.component';

describe('GoalOverviewComponent', () => {
  let component: GoalOverviewComponent;
  let fixture: ComponentFixture<GoalOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoalOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoalOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
