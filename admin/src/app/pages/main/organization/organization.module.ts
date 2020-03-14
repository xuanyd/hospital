import { NgModule } from '@angular/core';

import { CommonModule }       from '@angular/common';

import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

import { OrganizationRoutingModule } from './organization-routing.module';

import { OrganizationComponent } from './organization.component';

import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { UserService } from '../../../services/user.service';


@NgModule({
  imports: [OrganizationRoutingModule, NgZorroAntdModule, CommonModule, FormsModule, ReactiveFormsModule],
  declarations: [OrganizationComponent],
  exports: [],
  providers:    [ UserService ]
})
export class OrganizationModule { }
