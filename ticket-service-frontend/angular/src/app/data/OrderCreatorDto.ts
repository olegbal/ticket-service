export class OrderCreatorDto {
  constructor(public orderedTicketsIds: Array<number>,
              public userId: number) {
  }
}
