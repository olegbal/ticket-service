import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTicketsOverviewComponent } from './event-tickets-overview.component';

describe('EventTicketsOverviewComponent', () => {
  let component: EventTicketsOverviewComponent;
  let fixture: ComponentFixture<EventTicketsOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventTicketsOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventTicketsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
