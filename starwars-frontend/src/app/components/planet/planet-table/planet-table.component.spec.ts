import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PlanetTableComponent } from './planet-table.component';
import { Planet } from '../../../models/planet';
import { DatePipe } from '@angular/common';

describe('PlanetTableComponent', () => {
  let component: PlanetTableComponent;
  let fixture: ComponentFixture<PlanetTableComponent>;

  const mockPlanets: Planet[] = [
    { name: 'Alderaan', climate: 'temperate', gravity: '1 standard', terrain: 'grasslands, mountains', population: '2000000000', created: 'Dec 18, 2014' },
    { name: 'Aleen Minor', climate: 'unknown', gravity: 'unknown', terrain: 'unknown', population: 'unknown', created: 'Dec 20, 2014' },
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlanetTableComponent],
      providers: [DatePipe]
    }).compileComponents();

    fixture = TestBed.createComponent(PlanetTableComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should accept inputs correctly', () => {
    component.planets = mockPlanets;
    component.page = 2;
    component.totalPages = 5;

    expect(component.planets.length).toBe(2);
    expect(component.page).toBe(2);
    expect(component.totalPages).toBe(5);
  });

  it('should emit pageChange event on onPageChange', () => {
    spyOn(component.pageChange, 'emit');
    component.onPageChange(3);
    expect(component.pageChange.emit).toHaveBeenCalledWith(3);
  });

  it('should emit sortChange event on onSortChange', () => {
    spyOn(component.sortChange, 'emit');
    component.onSortChange('name');
    expect(component.sortChange.emit).toHaveBeenCalledWith('name');
  });
});
