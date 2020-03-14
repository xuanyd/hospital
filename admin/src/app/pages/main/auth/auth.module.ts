import { NgModule } from '@angular/core';

import { CommonModule }       from '@angular/common';

import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

import { AuthRoutingModule } from './auth-routing.module';

import { AuthComponent } from './auth.component';

import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { UserService } from '../../../services/user.service';

import { UserComponent } from './user/user.component';


@NgModule({
  imports: [AuthRoutingModule, NgZorroAntdModule, CommonModule, FormsModule, ReactiveFormsModule],
  declarations: [AuthComponent, UserComponent],
  exports: [AuthComponent],
  providers:    [ UserService ]
})
export class AuthModule { }
