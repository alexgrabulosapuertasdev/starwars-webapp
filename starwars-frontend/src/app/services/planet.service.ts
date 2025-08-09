import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { FindPaginationDTO } from "../models/common.types";
import { Observable } from "rxjs";
import { PlanetPaginatedResponse } from "../models/planet";

@Injectable({ providedIn: 'root' })
export class PlanetService extends ApiService {
  private readonly planetUrl: string = 'planet';

  findPlanets(value: FindPaginationDTO): Observable<PlanetPaginatedResponse> {
    return this.get(this.planetUrl, { params: { ...value } });
  }
}
