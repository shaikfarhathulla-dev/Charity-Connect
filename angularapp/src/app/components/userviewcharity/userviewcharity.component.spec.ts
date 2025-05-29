import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserviewcharityComponent } from './userviewcharity.component';

describe('UserviewcharityComponent', () => {
  let component: UserviewcharityComponent;
  let fixture: ComponentFixture<UserviewcharityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserviewcharityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserviewcharityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
