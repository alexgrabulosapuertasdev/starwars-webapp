import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NotFoundMessageComponent } from './not-found-message.component';
import { By } from '@angular/platform-browser';

describe('NotFoundMessageComponent', () => {
  let component: NotFoundMessageComponent;
  let fixture: ComponentFixture<NotFoundMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotFoundMessageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(NotFoundMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the no results message', () => {
    const h4 = fixture.debugElement.query(By.css('h4')).nativeElement;
    expect(h4.textContent).toContain('No results found');

    const p = fixture.debugElement.query(By.css('p')).nativeElement;
    expect(p.textContent).toContain('Try using a different search term.');
  });
});
