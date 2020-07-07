import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestsqaComponent } from './testsqa.component';

describe('TestsqaComponent', () => {
  let component: TestsqaComponent;
  let fixture: ComponentFixture<TestsqaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestsqaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestsqaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
