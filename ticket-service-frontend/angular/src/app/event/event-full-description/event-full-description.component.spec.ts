import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventFullDescriptionComponent } from './event-full-description.component';

describe('EventFullDescriptionComponent', () => {
  let component: EventFullDescriptionComponent;
  let fixture: ComponentFixture<EventFullDescriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventFullDescriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventFullDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
