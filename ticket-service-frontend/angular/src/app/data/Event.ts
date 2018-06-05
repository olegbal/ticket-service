import { EventPlace } from "./EventPlace";

export class Event {
  public constructor(public id: number,
                     public title: string,
                     public date: Date,
                     public imgUrl: string,
                     public eventPlace: EventPlace,
                     public minPrice: number,
                     public maxPrice: number,
                     public approved: boolean) {

  }
}
