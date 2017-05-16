import {Component, ViewEncapsulation} from '@angular/core';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-customclass',
  templateUrl: 'app/components/modal/custom-modal/custom-modal.html',
  encapsulation: ViewEncapsulation.None,
 // styleUrls:['/custom-modal.css']
  // styles: [`
  //   .dark-modal .modal-content {
  //     background-color: #292b2c;
     
  //   }
  //   .dark-modal .close {
  //     color: white;   
  //   }
  // `]
})
export class NgbdModalCustomclass {
  closeResult: string;

  constructor(private modalService: NgbModal) {}

  open(content) {
    this.modalService.open(content, { windowClass: 'abc' });
  }

}
