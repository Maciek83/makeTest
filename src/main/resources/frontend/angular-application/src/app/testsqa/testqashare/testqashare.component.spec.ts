import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestqashareComponent } from './testqashare.component';

describe('TestqashareComponent', () => {
  let component: TestqashareComponent;
  let fixture: ComponentFixture<TestqashareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestqashareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestqashareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
