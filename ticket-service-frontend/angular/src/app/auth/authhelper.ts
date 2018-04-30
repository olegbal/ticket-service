import { ApiHelper } from "../apiHelper";

export class AuthHelper {
  static loginUrl = "login";
  static apiLoginUrl = ApiHelper.v1 + AuthHelper.loginUrl;
  static registrationUrl = "register";
  static apiRegistrationUrl = ApiHelper.v1 + AuthHelper.registrationUrl;
  static authCookie = "auth";
}
