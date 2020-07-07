import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestqaComponent } from './testqa.component';

describe('TestqaComponent', () => {
  let component: TestqaComponent;
  let fixture: ComponentFixture<TestqaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestqaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestqaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
