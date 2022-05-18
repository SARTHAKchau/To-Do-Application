import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HightaskComponent } from './hightask.component';

describe('HightaskComponent', () => {
  let component: HightaskComponent;
  let fixture: ComponentFixture<HightaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HightaskComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HightaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
