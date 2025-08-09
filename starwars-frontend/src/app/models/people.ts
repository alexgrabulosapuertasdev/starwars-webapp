import { PaginatedResponse } from "./common.types";

export interface People {
  name: string;
  height: string;
  mass: string;
  birthYear: string;
  gender: string;
  created: string;
}

export interface PeoplePaginatedResponse extends PaginatedResponse<People> {}
