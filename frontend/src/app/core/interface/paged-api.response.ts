export interface PagedApiResponse<T> {
  message: string;
  data: {
    content: T[],
    page: number,
    size: number,
    totalPages: number,
    totalItems: number
  },
}
