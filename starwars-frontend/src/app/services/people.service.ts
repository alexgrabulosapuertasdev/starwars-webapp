import { Observable } from "rxjs";
import { ApiService } from "./api.service";
import { FindPaginationDTO } from "../models/common.types";
import { People, PeoplePaginatedResponse } from "../models/people";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class PeopleService extends ApiService {
  private readonly peopleUrl: string = 'people';

  findPeoples(value: FindPaginationDTO): Observable<PeoplePaginatedResponse> {
    return this.get(this.peopleUrl, { params: { ...value } });
  }
}
