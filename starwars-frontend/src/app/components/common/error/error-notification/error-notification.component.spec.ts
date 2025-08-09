import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { ErrorNotificationComponent } from './error-notification.component';

describe('ErrorNotificationComponent', () => {
  let component: ErrorNotificationComponent;
  let fixture: ComponentFixture<ErrorNotificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ErrorNotificationComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ErrorNotificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should display default title and message', () => {
    const titleEl = fixture.debugElement.query(By.css('h4')).nativeElement;
    const messageEl = fixture.debugElement.query(By.css('p')).nativeElement;

    expect(titleEl.textContent).toContain('Error loading data');
    expect(messageEl.textContent).toContain('Something went wrong while fetching the information.');
  });

  it('should display provided title and message', () => {
    component.title = 'Custom Error';
    component.message = 'Custom message content.';
    fixture.detectChanges();

    const titleEl = fixture.debugElement.query(By.css('h4')).nativeElement;
    const messageEl = fixture.debugElement.query(By.css('p')).nativeElement;

    expect(titleEl.textContent).toContain('Custom Error');
    expect(messageEl.textContent).toContain('Custom message content.');
  });

  it('should emit closeEvent when close button is clicked', () => {
    spyOn(component.closeEvent, 'emit');

    const button = fixture.debugElement.query(By.css('button'));
    button.triggerEventHandler('click', null);

    expect(component.closeEvent.emit).toHaveBeenCalled();
  });
});
