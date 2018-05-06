import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventBaseInfoComponent } from './event-base-info.component';

describe('EventBaseInfoComponent', () => {
  let component: EventBaseInfoComponent;
  let fixture: ComponentFixture<EventBaseInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventBaseInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventBaseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
