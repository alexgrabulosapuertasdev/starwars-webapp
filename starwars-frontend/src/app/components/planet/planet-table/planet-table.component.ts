import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Planet } from '../../../models/planet';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-planet-table',
  imports: [DatePipe],
  templateUrl: './planet-table.component.html',
  styleUrl: './planet-table.component.scss',
})
export class PlanetTableComponent {
  @Input({ required: true }) planets: Planet[] = [];
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
