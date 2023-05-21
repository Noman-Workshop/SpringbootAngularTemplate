import { PageCriteria } from "src/app/core/interface/page-criteria";

export interface TodoFilter {
  pagination: PageCriteria
  filters: {
    name: string,
    description: string,
    status: string,
    dueDate: Date,
    isImportant: Boolean
  }
}
