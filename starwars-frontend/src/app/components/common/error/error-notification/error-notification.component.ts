import { Component, EventEmitter, Input, Output } from "@angular/core";

@Component({
  selector: 'app-error-notification',
  templateUrl: './error-notification.component.html',
  styleUrls: ['./error-notification.component.scss'],
})
export class ErrorNotificationComponent {
  @Input({ required: true }) title: string = 'Error loading data';
  @Input({ required: true }) message: string = 'Something went wrong while fetching the information.';

  @Output() closeEvent = new EventEmitter<void>();

  onClose() {
    this.closeEvent.emit();
  }
}
