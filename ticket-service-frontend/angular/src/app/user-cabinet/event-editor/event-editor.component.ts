import { Component, OnInit } from '@angular/core';
import { EventService } from "../../event/event.service";
import { EventPlace } from "../../data/EventPlace";
import { FileUploader } from "ng2-file-upload";
import { ImageUploaderService } from "../../uploader/image-uploader.service";
import { HttpResponse } from "@angular/common/http";
import { EventPlaceService } from "../../event/event-place.service";
import { TicketCreatorDto } from "../../data/TicketCreatorDto";
import { TicketType } from "../../data/TicketType";
import { TicketService } from "../../ticket/ticket.service";
import { Event } from "../../data/Event";
import { Ticket } from "../../data/Ticket";
import { AccountEntryService } from "../../header/account-entry/account-entry.service";

@Component({
  selector: 'app-event-editor',
  templateUrl: './event-editor.component.html',
  styleUrls: ['./event-editor.component.css']
})
export class EventEditorComponent implements OnInit {

  eventTitle: string = "Event title";
  titleEditing: boolean = false;
  titleBeforeEditing: string = "";
  ticketCreatorDtos: TicketCreatorDto[] = [];
  availableEventPlaces: EventPlace[] = [];
  dateOfEvent: Date = new Date();
  editorFormEnabled: boolean = true;
  editingTicketItem: TicketCreatorDto = new TicketCreatorDto(new TicketType(0, "", ""), 0, 0);
  eventPlace: EventPlace = new EventPlace(0, "", "", "", "");
  imgUrl: string = "";
  uploader: FileUploader = new FileUploader({url: URL});
  ticketCreatorEnabled: boolean = false;

  ngOnInit() {
    this.eventPlaceService.getAllPlaces().subscribe((places: EventPlace[]) => {
      this.availableEventPlaces = places;
      this.eventPlace = this.availableEventPlaces[0] ? this.availableEventPlaces[0] : this.eventPlace;
    });
  }

  imageUrl: string = "assets/images/nophoto.png";

  constructor(private eventService: EventService,
              private uploadService: ImageUploaderService,
              private eventPlaceService: EventPlaceService,
              private ticketService: TicketService,
              private accountEntryService: AccountEntryService) {
  }

  startEditing() {
    this.editorFormEnabled = true;
  }

  saveContent() {

    let creatingEvent: Event = new Event(0, this.eventTitle, this.dateOfEvent, this.imageUrl, this.eventPlace, 0, 0, false);

    this.eventService.createEvent(creatingEvent, this.accountEntryService.loggedUser.id)
      .subscribe((receivedEvent: Event) => {
        this.ticketService.createTickets(this.ticketCreatorDtos, receivedEvent.id)
          .subscribe((listOfTickets: Ticket[]) => {
            alert(listOfTickets);
          })
      });
  }

  cancelContent() {
    this.editorFormEnabled = false;
    this.eventTitle = "Event title";
    this.eventPlace = this.availableEventPlaces[0] ? this.availableEventPlaces[0] : this.eventPlace;
    this.ticketCreatorDtos = [];
    this.imageUrl = "assets/images/nophoto.png";
  }

  selectFile(event) {
    const file = event.target.files.item(0);
    if (file.type.match('image.*')) {
      this.uploadService.pushFileToStorage(file).subscribe(loadEvent => {
        if (loadEvent instanceof HttpResponse) {
          console.log('File is completely uploaded!');
          this.imageUrl = "/api/v1/images/" + file.name;
        }
      })
    } else {
      alert('invalid format!');
    }
  }

  editTitle() {
    this.titleEditing = true;
    this.titleBeforeEditing = JSON.parse(JSON.stringify(this.eventTitle));
  }

  saveTitle() {
    this.titleEditing = false;
  }

  cancelEditingTitle() {
    this.eventTitle = JSON.parse(JSON.stringify(this.titleBeforeEditing));
    this.titleEditing = false;

  }

  removeSelection(ticket: TicketCreatorDto) {
    this.ticketCreatorDtos = this.ticketCreatorDtos.filter(x => x.ticketType.typeDescription != ticket.ticketType.typeDescription);
  }

  openTicketEditor() {
    this.ticketCreatorEnabled = true;
    this.editingTicketItem = new TicketCreatorDto(new TicketType(0, "", ""), 0, 0);
  }

  saveCreatingTicket() {
    this.ticketCreatorDtos.push(this.editingTicketItem);
    this.editingTicketItem = new TicketCreatorDto(new TicketType(0, "", ""), 0, 0);
    this.ticketCreatorEnabled = false;
  }

  cancelCreatingTicket() {
    this.editingTicketItem = new TicketCreatorDto(new TicketType(0, "", ""), 0, 0);
    this.ticketCreatorEnabled = false;
  }
}

const URL = '/api/v1/images/upload';
