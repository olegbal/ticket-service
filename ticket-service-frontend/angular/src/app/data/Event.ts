export class Event {
  public constructor(public id: number,
                     public title: string,
                     public date: Date,
                     public minPrice: number,
                     public maxPrice: number,
                     public imgUrl: string,
                     public address: string) {

  }


}
