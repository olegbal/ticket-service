export class RegistrationData {
  constructor(public login: string,
              public email: string,
              public password: string,
              public firstName: string,
              public lastName: string,
              public organization: string,
              public phoneNumber: string,
              public secretCode?: string) {
  }
}
