import { Component, Input } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-content',
  templateUrl: "app/components/dashboard-body/modal.html"
})
export class NgbdModalContent {
  @Input() name;

  constructor(public activeModal: NgbActiveModal) { } 
}