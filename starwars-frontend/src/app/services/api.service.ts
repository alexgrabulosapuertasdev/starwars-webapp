import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../environment/environment.development";

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly url: string = `${environment.API_URL}`;

  constructor(
    private readonly http: HttpClient,
  ) {}

  protected get<T>(path: string, options: { params?: Record<string, string | number> } = {}): Observable<T> {
    return this.http.get<T>(`${this.url}${path}`, options);
  }
}
