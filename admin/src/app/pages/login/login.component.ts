import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { LocalStorage } from '../../util/local.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  isLogin = false;
  loginForm: FormGroup;
  imgCode: String="";
  imgCodeUUID: String="";

  constructor(private router: Router,
      private http:HttpClient,
      private ls: LocalStorage, 
      private fb: FormBuilder) {
      
  }

  ngOnInit() {
      this.getValid();
      this.loginForm=this.fb.group({
          password: [ '', [Validators.required] ],
          username: [ '', [Validators.required] ],
          validCode: ['', [Validators.required]]
      })
  }

  submit(): void {
      for (const i in this.loginForm.controls) {
          this.loginForm.controls[i].markAsDirty();
          this.loginForm.controls[i].updateValueAndValidity();
      }
      if(this.loginForm.status == "INVALID")
          return;
      let params = {
          username: this.loginForm.value.username, 
          password:this.loginForm.value.password,
          code:this.loginForm.value.validCode,
          type:'admin',
          secret:this.imgCodeUUID
      };
      let that = this;
      that.isLogin = true;
      this.http.post("auth/token", params).subscribe(res=>{
        that.isLogin = false;
        if(res['code'] === 200) {
            that.ls.setObject('userName', that.loginForm.value.userName);
            that.ls.set('token', res['token']);
            that.router.navigate(["/main"]);
        } else {
            alert(res['message']);
            that.getValid();
        }
      });
  }

  reset(): void {
    this.loginForm.reset()
  }

  getValid() {
    this.http.post("auth/verifyCode",{type:"admin"}).subscribe(res=>{
      if(res['code'] === 200) {
        this.imgCode = res['image'].replace(/\ +/g, "").replace(/[\r\n]/g, "");
        this.imgCodeUUID = res['secret'];
      }
    });
  }

}
