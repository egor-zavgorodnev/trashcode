import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  name = this.cookieService.get('Name')
  address = this.cookieService.get('Address')
  
  constructor(private cookieService: CookieService) { }

  ngOnInit(): void {
  }

}
