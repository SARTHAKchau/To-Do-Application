import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Dialogbox2Component } from './dialogbox2.component';

describe('Dialogbox2Component', () => {
  let component: Dialogbox2Component;
  let fixture: ComponentFixture<Dialogbox2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Dialogbox2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Dialogbox2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
