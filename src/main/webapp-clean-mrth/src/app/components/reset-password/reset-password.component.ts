import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

import { ResetPasswordInterface } from './reset-password.interface'
import { LoginService } from '../../services/login-services/login.service'

@Component({
  selector: 'reset-password',
  templateUrl: 'reset-password.html',
  //styleUrls: ['']
})
export class ResetPasswordComponent implements OnInit {
  public resetpassword: ResetPasswordInterface;
  private resetPasswordData: any;
  private errorUserID: string = "";
  private errorEmailID: string = "";
  private invalidCreds: boolean = false;
  private successResetPasswordMessage: string = "";

  constructor(private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    this.resetpassword = {
      sid_tid: "",
      emailId: ""
    }
  }
  private usernameRegex(uname: string) {
    if (uname.length == 7) {
      var re = (/[s|t|S|T]{1}[0-9]{4,5}[A-Z|a-z]{1,2}/)
      return re.test(uname)
    } else {
      return false
    }
  }
  private emailRegex(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }
  private resetPassword() {
    if (this.resetpassword.sid_tid.trim() === "" && this.resetpassword.emailId.trim() === "") {
      this.errorUserID = "Please enter your SID/TID.";
      this.errorEmailID = "Email is required.";
      return;
    } else if (this.resetpassword.sid_tid.trim() === "" && this.resetpassword.emailId.trim() !== null && (!this.emailRegex(this.resetpassword.emailId.trim()))) {
      this.errorUserID = "Please enter your SID/TID.";
      this.errorEmailID = "Enter the valid Email ID.";
      return;
    } else if (this.resetpassword.sid_tid.trim() !== null && (!this.usernameRegex(this.resetpassword.sid_tid.trim())) && this.resetpassword.emailId.trim() === "") {
       this.errorUserID = "Please enter valid SID/TID.";
      this.errorEmailID = 'Email is required'
      return;
    }
    this.loginService.resetPassword(this.resetpassword.sid_tid.trim(), this.resetpassword.emailId.trim()).subscribe(
      (resetPasswordData) => {
        this.resetPasswordData = (resetPasswordData)
        this.successResetPasswordMessage = "Please check your email for new UserID and Password";
      },
      (error) => {
        this.invalidCreds = true;
        //this.errorMessage = "Please enter your valid SID/TID and password";
      }
    )
    //debugger
  }

  private cancel() {
    let url = ["login"]
    this.router.navigate(url);
  }

}
