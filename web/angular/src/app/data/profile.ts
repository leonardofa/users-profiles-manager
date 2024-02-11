import {User} from "./user";

export interface Profile {
  id: string;
  name: string;
  description: string;
  users: User[];
}
