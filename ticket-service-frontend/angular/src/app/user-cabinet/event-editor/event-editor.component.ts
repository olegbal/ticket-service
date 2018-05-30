import { Component, OnInit } from '@angular/core';
import { EventService } from "../../event/event.service";
import { Event } from "../../data/Event";

@Component({
  selector: 'app-event-editor',
  templateUrl: './event-editor.component.html',
  styleUrls: ['./event-editor.component.css']
})
export class EventEditorComponent implements OnInit {

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
  }


  editorFormEnabled: boolean = false;
  creatingEvent: Event;

  startEditing() {
    this.editorFormEnabled = true;
    this.creatingEvent = new Event(-1, "Event title", new Date(Date.now()), "", null, 0, 0, false)
  }

  clearContent() {
    this.editorFormEnabled = false;
  }

  saveContent() {

  }
}
