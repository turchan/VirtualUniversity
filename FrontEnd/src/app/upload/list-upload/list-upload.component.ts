import { Component, OnInit }   from '@angular/core';
import { Observable }          from 'rxjs';
import { TokenStorageService } from '../../auth/token-storage.service';
import { UploadFileService }   from '../../services/upload-file.service';

@Component({
  selector: 'list-upload',
  templateUrl: './list-upload.component.html',
  styleUrls: ['./list-upload.component.css']
})
export class ListUploadComponent implements OnInit {

  showFile = false;
  fileUploads: Observable<string[]>;
  private info: any;

  constructor(private uploadService: UploadFileService,
              private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      login: this.token.getLogin(),
      authorities: this.token.getAuthorities()
    }
  }
}
