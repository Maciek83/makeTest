import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddtestqaComponent } from './addtestqa.component';

describe('AddtestqaComponent', () => {
  let component: AddtestqaComponent;
  let fixture: ComponentFixture<AddtestqaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddtestqaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddtestqaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
