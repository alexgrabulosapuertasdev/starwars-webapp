export interface PaginatedResponse<T> {
  results: T[];
  page: number;
  total: number;
}

export interface FindPaginationDTO extends SortDTO {
  search: string;
  page: number;
}

interface SortDTO {
  orderBy: string;
  orderDirection: 'asc' | 'desc';
}
