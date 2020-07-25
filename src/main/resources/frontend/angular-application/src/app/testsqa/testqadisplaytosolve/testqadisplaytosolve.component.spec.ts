import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestqadisplaytosolveComponent } from './testqadisplaytosolve.component';

describe('TestqadisplaytosolveComponent', () => {
  let component: TestqadisplaytosolveComponent;
  let fixture: ComponentFixture<TestqadisplaytosolveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestqadisplaytosolveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestqadisplaytosolveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
