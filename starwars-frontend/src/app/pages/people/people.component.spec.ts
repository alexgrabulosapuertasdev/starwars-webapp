import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { PeopleComponent } from './people.component';
import { PeopleService } from '../../services/people.service';
import { of, throwError } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';

describe('PeopleComponent', () => {
  let component: PeopleComponent;
  let fixture: ComponentFixture<PeopleComponent>;
  let peopleServiceSpy: jasmine.SpyObj<PeopleService>;

  const fakeResponse = {
    page: 1,
    total: 5,
    results: [
      { name: 'Ackbar', height: '180', mass: '83', birthYear: '41BBY', gender: 'male', created: 'Dec 18, 2014' },
      { name: 'Adi Gallia', height: '184', mass: '50', birthYear: 'unknown', gender: 'female', created: 'Dec 20, 2014' },
      { name: 'Anakin Skywalker', height: '188', mass: '84', birthYear: '41.9BBY', gender: 'male', created: 'Dec 10, 2014' },
      { name: 'Arvel Crynyd', height: 'unknown', mass: 'unknown', birthYear: 'unknown', gender: 'male', created: 'Dec 18, 2014' },
      { name: 'Ayla Secura', height: '178', mass: '55', birthYear: '48BBY', gender: 'female', created: 'Dec 20, 2014' },
    ]
  };

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('PeopleService', ['findPeoples']);

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, PeopleComponent],
      providers: [{ provide: PeopleService, useValue: spy }]
    }).compileComponents();

    peopleServiceSpy = TestBed.inject(PeopleService) as jasmine.SpyObj<PeopleService>;
    peopleServiceSpy.findPeoples.and.returnValue(of(fakeResponse));

    fixture = TestBed.createComponent(PeopleComponent);
    component = fixture.componentInstance;
  });

  it('should create and fetch initial peoples', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
    expect(peopleServiceSpy.findPeoples).toHaveBeenCalledTimes(1);
    expect(component.paginatedPeoples).toEqual(fakeResponse);
    expect(component.totalPages).toBe(1);
  });

  it('should fetch peoples on search change (debounced)', fakeAsync(() => {
    fixture.detectChanges();
    peopleServiceSpy.findPeoples.calls.reset();
    component.form.get('search')?.setValue('Anakin');
    tick(300);
    expect(component.findPeopleDTO.search).toBe('Anakin');
    expect(component.findPeopleDTO.page).toBe(1);
    expect(peopleServiceSpy.findPeoples).toHaveBeenCalledTimes(1);
  }));

  it('should update search and fetch peoples on onSearchChange()', () => {
    fixture.detectChanges();
    peopleServiceSpy.findPeoples.calls.reset();
    component.form.get('search')?.setValue('Ayla');
    component.onSearchChange();
    expect(component.findPeopleDTO.search).toBe('Ayla');
    expect(peopleServiceSpy.findPeoples).toHaveBeenCalledTimes(1);
  });

  it('should update page and fetch peoples on onPageChange()', () => {
    fixture.detectChanges();
    peopleServiceSpy.findPeoples.calls.reset();
    component.onPageChange(2);
    expect(component.findPeopleDTO.page).toBe(2);
    expect(peopleServiceSpy.findPeoples).toHaveBeenCalledTimes(1);
  });

  it('should toggle sort direction if same column and fetch peoples on onSortChange()', () => {
    fixture.detectChanges();
    peopleServiceSpy.findPeoples.calls.reset();
    component.findPeopleDTO.orderBy = 'name';
    component.findPeopleDTO.orderDirection = 'asc';
    component.onSortChange('name');
    expect(component.findPeopleDTO.orderDirection).toBe('desc');
    expect(component.findPeopleDTO.page).toBe(1);
    expect(peopleServiceSpy.findPeoples).toHaveBeenCalledTimes(1);
  });

  it('should set new sort column asc and fetch peoples on onSortChange()', () => {
    fixture.detectChanges();
    peopleServiceSpy.findPeoples.calls.reset();
    component.findPeopleDTO.orderBy = 'name';
    component.findPeopleDTO.orderDirection = 'desc';
    component.onSortChange('height');
    expect(component.findPeopleDTO.orderBy).toBe('height');
    expect(component.findPeopleDTO.orderDirection).toBe('asc');
    expect(component.findPeopleDTO.page).toBe(1);
    expect(peopleServiceSpy.findPeoples).toHaveBeenCalledTimes(1);
  });
});
