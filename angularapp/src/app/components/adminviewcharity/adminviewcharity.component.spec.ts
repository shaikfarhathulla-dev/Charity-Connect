import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewcharityComponent } from './adminviewcharity.component';

describe('AdminviewcharityComponent', () => {
  let component: AdminviewcharityComponent;
  let fixture: ComponentFixture<AdminviewcharityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminviewcharityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminviewcharityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
