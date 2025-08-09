import { Component } from '@angular/core';
import { PeopleTableComponent } from '../../components/people/people-table/people-table.component';
import { PeoplePaginatedResponse } from '../../models/people';
import { PeopleService } from '../../services/people.service';
import { FindPaginationDTO } from '../../models/common.types';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs';
import { NotFoundMessageComponent } from '../../components/common/not-found/not-found-message/not-found-message.component';
import { ErrorNotificationComponent } from '../../components/common/error/error-notification/error-notification.component';

@Component({
  selector: 'app-people.component',
  imports: [PeopleTableComponent, ReactiveFormsModule, NotFoundMessageComponent, ErrorNotificationComponent],
  templateUrl: './people.component.html',
  styleUrl: './people.component.scss',
  standalone: true,
})
export class PeopleComponent {
  paginatedPeoples: PeoplePaginatedResponse = {
    page: 1,
    total: 0,
    results: [],
  };
  totalPages: number = 1;
  form = new FormGroup({
    search: new FormControl({ value: '', disabled: false }),
  });
  findPeopleDTO: FindPaginationDTO = {
    search: '',
    page: 1,
    orderBy: 'name',
    orderDirection: 'asc',
  };
  isSubmitted: boolean = false;
  error: { title: string; message: string } | null = null;

  constructor(
    private readonly peopleService: PeopleService,
  ) {
    
    this.form.get('search')?.valueChanges
      .pipe(debounceTime(300))
      .subscribe(value => {
        this.findPeopleDTO.search = value ?? '';
        this.findPeopleDTO.page = 1;
        this.fetchPeoples(this.findPeopleDTO);
      });

    this.fetchPeoples(this.findPeopleDTO);
  }

  private fetchPeoples(findPeopleDTO: FindPaginationDTO): void {
    this.peopleService.findPeoples(findPeopleDTO).subscribe({
      next: (data) => {
        this.isSubmitted = true;
        this.paginatedPeoples = data;
        this.totalPages = Math.ceil(data.total / 15);
      },
      error: (error) => {
        this.isSubmitted = true;
        this.error = {
          title: 'Error fetching peoples',
          message: 'An unexpected error occurred while fetching peoples.',
        };
        console.error(`Error at find the peoples: ${error}`);
      }
    });
  }

  onSearchChange(): void {
    this.findPeopleDTO.search = this.form.value.search ?? '';

    this.fetchPeoples(this.findPeopleDTO);
  }

  onPageChange(page: number): void {
    this.findPeopleDTO.page = page;
    this.fetchPeoples(this.findPeopleDTO);
  }

  onSortChange(sortBy: string): void {
    if (sortBy === this.findPeopleDTO.orderBy) {
      this.findPeopleDTO.orderDirection = this.findPeopleDTO.orderDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.findPeopleDTO.orderDirection = 'asc';
      this.findPeopleDTO.orderBy = sortBy;
    }

    this.findPeopleDTO.page = 1;

    this.fetchPeoples(this.findPeopleDTO);
  }

  closeNotificationError(): void {
    this.error = null;
  }
}
