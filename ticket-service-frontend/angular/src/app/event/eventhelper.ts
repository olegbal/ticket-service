import { ApiHelper } from "../apiHelper";

export class EventHelper {
  static eventUrl = "events";
  static apiEventUrl = ApiHelper.v1 + EventHelper.eventUrl;
  static routeEventDetails = "events/:id";
  static apiEventDetails = EventHelper.apiEventUrl + "/"
}
