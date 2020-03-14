import { NgModule } from '@angular/core';

import { CommonModule }       from '@angular/common';

import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

import { NoticeRoutingModule } from './notice-routing.module';

import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { NoticeService } from '../../../services/notice.service';

import { NoticeComponent } from './notice.component';


@NgModule({
  imports: [NoticeRoutingModule, NgZorroAntdModule, CommonModule, FormsModule, ReactiveFormsModule],
  declarations: [NoticeComponent],
  exports: [NoticeComponent],
  providers:    [ NoticeService ]
})
export class NoticeModule { }
