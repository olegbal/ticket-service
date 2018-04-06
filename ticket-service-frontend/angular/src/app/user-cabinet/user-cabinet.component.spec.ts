import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCabinetComponent } from './user-cabinet.component';

describe('UserCabinetComponent', () => {
  let component: UserCabinetComponent;
  let fixture: ComponentFixture<UserCabinetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCabinetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCabinetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
