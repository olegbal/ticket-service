import { Role } from "./Role";

export class User {
  constructor(public id: number,
              public login: string,
              public password: string,
              public firstname: string,
              public lastname: string,
              public phoneNumber: string,
              public email: string,
              public organization: string,
              public roles: Role[]) {
  }
}
