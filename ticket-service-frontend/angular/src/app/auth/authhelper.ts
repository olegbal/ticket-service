import {ApiHelper} from "../apiHelper";

export class AuthHelper {
  static loginUrl = ApiHelper.v1 + "login";
  static registrationUrl = ApiHelper.v1 + "register";
  static authCookie = ApiHelper.v1 + "auth";
}
