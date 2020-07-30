import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestshareformComponent } from './testshareform.component';

describe('TestshareformComponent', () => {
  let component: TestshareformComponent;
  let fixture: ComponentFixture<TestshareformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestshareformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestshareformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
