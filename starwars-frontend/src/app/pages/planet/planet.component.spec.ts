import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { PlanetComponent } from './planet.component';
import { PlanetService } from '../../services/planet.service';
import { of, throwError } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';

describe('PlanetComponent', () => {
  let component: PlanetComponent;
  let fixture: ComponentFixture<PlanetComponent>;
  let planetServiceSpy: jasmine.SpyObj<PlanetService>;

  const fakeResponse = {
    page: 1,
    total: 5,
    results: [
      { name: 'Alderaan', climate: 'temperate', gravity: '1 standard', terrain: 'grasslands, mountains', population: '2000000000', created: 'Dec 18, 2014' },
      { name: 'Aleen Minor', climate: 'unknown', gravity: 'unknown', terrain: 'unknown', population: 'unknown', created: 'Dec 20, 2014' },
      { name: 'Bespin', climate: 'temperate', gravity: '1.5 (surface), 1 standard (Cloud City)', terrain: 'gas giant', population: '6000000', created: 'Dec 10, 2014' },
      { name: 'Bestine IV', climate: 'temperate', gravity: 'unknown', terrain: 'rocky islands, oceans', population: '62000000', created: 'Dec 18, 2014' },
      { name: 'Cato Neimoidia', climate: 'temperate, moist', gravity: '1 standard', terrain: 'mountains, fields, forests, rock arches', population: '10000000', created: 'Dec 20, 2014' },
    ],
  };

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('PlanetService', ['findPlanets']);

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, PlanetComponent],
      providers: [{ provide: PlanetService, useValue: spy }]
    }).compileComponents();

    planetServiceSpy = TestBed.inject(PlanetService) as jasmine.SpyObj<PlanetService>;
    planetServiceSpy.findPlanets.and.returnValue(of(fakeResponse));

    fixture = TestBed.createComponent(PlanetComponent);
    component = fixture.componentInstance;
  });

  it('should create and fetch initial planets', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
    expect(planetServiceSpy.findPlanets).toHaveBeenCalledTimes(1);
    expect(component.paginatedPlanets).toEqual(fakeResponse);
    expect(component.totalPages).toBe(1);
  });

  it('should fetch planets on search change (debounced)', fakeAsync(() => {
    fixture.detectChanges();
    planetServiceSpy.findPlanets.calls.reset();
    component.form.get('search')?.setValue('Anakin');
    tick(300);
    expect(component.findPlanetDTO.search).toBe('Anakin');
    expect(component.findPlanetDTO.page).toBe(1);
    expect(planetServiceSpy.findPlanets).toHaveBeenCalledTimes(1);
  }));

  it('should update search and fetch planets on onSearchChange()', () => {
    fixture.detectChanges();
    planetServiceSpy.findPlanets.calls.reset();
    component.form.get('search')?.setValue('Ayla');
    component.onSearchChange();
    expect(component.findPlanetDTO.search).toBe('Ayla');
    expect(planetServiceSpy.findPlanets).toHaveBeenCalledTimes(1);
  });

  it('should update page and fetch planets on onPageChange()', () => {
    fixture.detectChanges();
    planetServiceSpy.findPlanets.calls.reset();
    component.onPageChange(2);
    expect(component.findPlanetDTO.page).toBe(2);
    expect(planetServiceSpy.findPlanets).toHaveBeenCalledTimes(1);
  });

  it('should toggle sort direction if same column and fetch planets on onSortChange()', () => {
    fixture.detectChanges();
    planetServiceSpy.findPlanets.calls.reset();
    component.findPlanetDTO.orderBy = 'name';
    component.findPlanetDTO.orderDirection = 'asc';
    component.onSortChange('name');
    expect(component.findPlanetDTO.orderDirection).toBe('desc');
    expect(component.findPlanetDTO.page).toBe(1);
    expect(planetServiceSpy.findPlanets).toHaveBeenCalledTimes(1);
  });

  it('should set new sort column asc and fetch planets on onSortChange()', () => {
    fixture.detectChanges();
    planetServiceSpy.findPlanets.calls.reset();
    component.findPlanetDTO.orderBy = 'name';
    component.findPlanetDTO.orderDirection = 'desc';
    component.onSortChange('height');
    expect(component.findPlanetDTO.orderBy).toBe('height');
    expect(component.findPlanetDTO.orderDirection).toBe('asc');
    expect(component.findPlanetDTO.page).toBe(1);
    expect(planetServiceSpy.findPlanets).toHaveBeenCalledTimes(1);
  });
});
