import { Component, EventEmitter, Input, Output } from '@angular/core';
import { People } from '../../../models/people';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-people-table',
  imports: [DatePipe],
  templateUrl: './people-table.component.html',
  styleUrl: './people-table.component.scss',
})
export class PeopleTableComponent {
  @Input({ required: true }) peoples: People[] = [];
  @Input({ required: true }) page: number = 1;
  @Input({ required: true }) totalPages: number = 1;

  @Output() pageChange = new EventEmitter<number>();
  @Output() sortChange = new EventEmitter<string>();

  onPageChange(page: number): void {
    this.pageChange.emit(page);
  }

  onSortChange(sortBy: string): void {
    this.sortChange.emit(sortBy);
  }
}
