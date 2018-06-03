export class ChangePasswordDto {
  constructor(public id: number,
              public currentPassword: string,
              public newPassword: string) {
  }
}
