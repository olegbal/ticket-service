import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderSuccessComponentComponent } from './order-success-component.component';

describe('OrderSuccessComponentComponent', () => {
  let component: OrderSuccessComponentComponent;
  let fixture: ComponentFixture<OrderSuccessComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderSuccessComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderSuccessComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
