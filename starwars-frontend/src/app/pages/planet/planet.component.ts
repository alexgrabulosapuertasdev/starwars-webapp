import { Component } from '@angular/core';
import { PlanetTableComponent } from '../../components/planet/planet-table/planet-table.component';
import { PlanetPaginatedResponse } from '../../models/planet';
import { PlanetService } from '../../services/planet.service';
import { FindPaginationDTO } from '../../models/common.types';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs';
import { NotFoundMessageComponent } from '../../components/common/not-found/not-found-message/not-found-message.component';

@Component({
  selector: 'app-planet.component',
  imports: [PlanetTableComponent, ReactiveFormsModule, NotFoundMessageComponent],
  templateUrl: './planet.component.html',
  styleUrl: './planet.component.scss',
  standalone: true,
})
export class PlanetComponent {
  paginatedPlanets: PlanetPaginatedResponse = {
    page: 1,
    total: 0,
    results: [],
  };
  totalPages: number = 1;
  form = new FormGroup({
    search: new FormControl({ value: '', disabled: false }),
  });
  findPlanetDTO: FindPaginationDTO = {
    search: '',
    page: 1,
    orderBy: 'name',
    orderDirection: 'asc',
  };
  isSubmitted: boolean = false;

  constructor(
    private readonly planetService: PlanetService,
  ) {
    
    this.form.get('search')?.valueChanges
      .pipe(debounceTime(300))
      .subscribe(value => {
        this.findPlanetDTO.search = value ?? '';
        this.findPlanetDTO.page = 1;
        this.fetchPlanets(this.findPlanetDTO);
      });

    this.fetchPlanets(this.findPlanetDTO);
  }

  private fetchPlanets(findPlanetDTO: FindPaginationDTO): void {
    this.planetService.findPlanets(findPlanetDTO).subscribe({
      next: (data) => {
        this.isSubmitted = true;
        this.paginatedPlanets = data;
        this.totalPages = Math.ceil(data.total / 15);
      },
      error: (error) => {
        this.isSubmitted = true;
        console.error(`Error at find the planets: ${error}`);
      }
    });
  }

  onSearchChange(): void {
    this.findPlanetDTO.search = this.form.value.search ?? '';

    this.fetchPlanets(this.findPlanetDTO);
  }

  onPageChange(page: number): void {
    this.findPlanetDTO.page = page;
    this.fetchPlanets(this.findPlanetDTO);
  }

  onSortChange(sortBy: string): void {
    if (sortBy === this.findPlanetDTO.orderBy) {
      this.findPlanetDTO.orderDirection = this.findPlanetDTO.orderDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.findPlanetDTO.orderDirection = 'asc';
      this.findPlanetDTO.orderBy = sortBy;
    }

    this.findPlanetDTO.page = 1;

    this.fetchPlanets(this.findPlanetDTO);
  }
}
