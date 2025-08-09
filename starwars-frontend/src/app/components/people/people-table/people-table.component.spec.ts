import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PeopleTableComponent } from './people-table.component';
import { People } from '../../../models/people';
import { DatePipe } from '@angular/common';

describe('PeopleTableComponent', () => {
  let component: PeopleTableComponent;
  let fixture: ComponentFixture<PeopleTableComponent>;

  const mockPeoples: People[] = [
    { name: 'Ackbar', height: '180', mass: '83', birthYear: '41BBY', gender: 'male', created: 'Dec 18, 2014' },
    { name: 'Adi Gallia', height: '184', mass: '50', birthYear: 'unknown', gender: 'female', created: 'Dec 20, 2014' },
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PeopleTableComponent],
      providers: [DatePipe]
    }).compileComponents();

    fixture = TestBed.createComponent(PeopleTableComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should accept inputs correctly', () => {
    component.peoples = mockPeoples;
    component.page = 2;
    component.totalPages = 5;

    expect(component.peoples.length).toBe(2);
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
