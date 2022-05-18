import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LowtaskComponent } from './lowtask.component';

describe('LowtaskComponent', () => {
  let component: LowtaskComponent;
  let fixture: ComponentFixture<LowtaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LowtaskComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LowtaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
