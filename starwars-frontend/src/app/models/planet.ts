import { PaginatedResponse } from "./common.types";

export interface Planet {
  name: string;
  climate: string;
  gravity: string;
  terrain: string;
  population: string;
  created: string;
}

export interface PlanetPaginatedResponse extends PaginatedResponse<Planet> {}
