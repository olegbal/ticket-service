import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventPlaceMapComponent } from './event-place-map.component';

describe('EventPlaceMapComponent', () => {
  let component: EventPlaceMapComponent;
  let fixture: ComponentFixture<EventPlaceMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventPlaceMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventPlaceMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
