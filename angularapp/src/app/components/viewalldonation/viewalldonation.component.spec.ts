import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewalldonationComponent } from './viewalldonation.component';

describe('ViewalldonationComponent', () => {
  let component: ViewalldonationComponent;
  let fixture: ComponentFixture<ViewalldonationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewalldonationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewalldonationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
