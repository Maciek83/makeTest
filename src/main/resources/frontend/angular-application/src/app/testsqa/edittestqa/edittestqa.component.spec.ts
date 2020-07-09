import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EdittestqaComponent } from './edittestqa.component';

describe('EdittestqaComponent', () => {
  let component: EdittestqaComponent;
  let fixture: ComponentFixture<EdittestqaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EdittestqaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EdittestqaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
