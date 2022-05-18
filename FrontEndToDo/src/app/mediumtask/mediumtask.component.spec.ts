import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediumtaskComponent } from './mediumtask.component';

describe('MediumtaskComponent', () => {
  let component: MediumtaskComponent;
  let fixture: ComponentFixture<MediumtaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MediumtaskComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MediumtaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
