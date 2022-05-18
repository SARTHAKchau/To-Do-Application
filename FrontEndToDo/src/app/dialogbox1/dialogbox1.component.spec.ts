import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Dialogbox1Component } from './dialogbox1.component';

describe('Dialogbox1Component', () => {
  let component: Dialogbox1Component;
  let fixture: ComponentFixture<Dialogbox1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Dialogbox1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Dialogbox1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
