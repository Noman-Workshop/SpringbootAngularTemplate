import { PageCriteria } from "src/app/core/interface/page-criteria";

export interface UserFilter {
  pagination: PageCriteria
  filters: {
    name: string
  }
}
