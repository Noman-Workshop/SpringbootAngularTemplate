import { PageCriteria } from "src/app/core/interface/page-criteria";

export interface RoleFilter {
  pagination: PageCriteria
  filters: {
    name: string
  }
}
