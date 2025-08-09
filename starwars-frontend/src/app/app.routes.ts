import { Routes } from '@angular/router';
import { PeopleComponent } from './pages/people/people.component';
import { PlanetComponent } from './pages/planet/planet.component';

export const routes: Routes = [
  { path: '', redirectTo: '/people', pathMatch: 'full' },
  { path: 'people', component: PeopleComponent },
  { path: 'planets', component: PlanetComponent },
  { path: '**', redirectTo: '/people' },
];
