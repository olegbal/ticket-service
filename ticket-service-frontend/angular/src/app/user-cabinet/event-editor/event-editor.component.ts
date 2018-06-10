import {Component, OnInit} from '@angular/core';
import {EventService} from "../../event/event.service";
import {Ticket} from "../../data/Ticket";
import {EventPlace} from "../../data/EventPlace";
import {FileUploader} from "ng2-file-upload";
import {ImageUploaderService} from "../../uploader/image-uploader.service";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-event-editor',
  templateUrl: './event-editor.component.html',
  styleUrls: ['./event-editor.component.css']
})
export class EventEditorComponent implements OnInit {

  constructor(private eventService: EventService,
              private uploadService: ImageUploaderService) {
  }

  ngOnInit() {
  }


  editorFormEnabled: boolean = false;
  dateOfEvent: Date = new Date();
  ticketList: Ticket[] = [];
  eventPlace: EventPlace = new EventPlace(0, "", "", "", "");
  imgUrl: string = "";
  uploader: FileUploader = new FileUploader({url: URL});
  imageUrl: string = "assets/images/nophoto.png";

  startEditing() {
    this.editorFormEnabled = true;
  }

  clearContent() {
    this.editorFormEnabled = false;
  }

  saveContent() {

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

  openEventPlaceSelector() {

  }
}

const URL = '/api/v1/images/upload';
