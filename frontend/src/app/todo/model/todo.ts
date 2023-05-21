export interface Todo {
  createdAt: Date;
  updatedAt: Date;
  id: string;
  name: string;
  description: string;
  dueDate: Date;
  status: string;
  isImportant: Boolean;
}

