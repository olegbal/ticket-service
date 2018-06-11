import { Component, OnInit } from '@angular/core';
import { EventService } from "../../event/event.service";
import { Ticket } from "../../data/Ticket";
import { EventPlace } from "../../data/EventPlace";
import { FileUploader } from "ng2-file-upload";
import { ImageUploaderService } from "../../uploader/image-uploader.service";
import { HttpResponse } from "@angular/common/http";
import { EventPlaceService } from "../../event/event-place.service";

@Component({
  selector: 'app-event-editor',
  templateUrl: './event-editor.component.html',
  styleUrls: ['./event-editor.component.css']
})
export class EventEditorComponent implements OnInit {

  eventTitle: string = "Event title";
  titleEditing: boolean = false;
  titleBeforeEditing: string = "";
  ticketList: Ticket[] = null;
  availableEventPlaces: EventPlace[] = [];
  dateOfEvent: Date = new Date();
  editorFormEnabled: boolean = true;

  constructor(private eventService: EventService,
              private uploadService: ImageUploaderService,
              private eventPlaceService: EventPlaceService) {
  }
  eventPlace: EventPlace = new EventPlace(0, "", "", "", "");
  imgUrl: string = "";
  uploader: FileUploader = new FileUploader({url: URL});

  ngOnInit() {
    this.eventPlaceService.getAllPlaces().subscribe((places: EventPlace[]) => {
      this.availableEventPlaces = places;
      this.eventPlace = this.availableEventPlaces[0] ? this.availableEventPlaces[0] : this.eventPlace;
    });
  }
  imageUrl: string = "assets/images/nophoto.png";

  startEditing() {
    this.editorFormEnabled = true;
  }

  saveContent() {

  }

  cancelContent() {
    this.editorFormEnabled = false;
    this.eventTitle = "Event title";
    this.eventPlace = this.availableEventPlaces[0] ? this.availableEventPlaces[0] : this.eventPlace;
    this.ticketList = [];
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

  openTicketEditor() {

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

  removeSelection(ticket: Ticket) {
    this.ticketList = this.ticketList.filter(x => x.ticketType.typeDescription != ticket.ticketType.typeDescription);
  }
}

const URL = '/api/v1/images/upload';
