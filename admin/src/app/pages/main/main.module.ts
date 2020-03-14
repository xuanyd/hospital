import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common'

import { MainRoutingModule } from './main-routing.module';

import { MainComponent } from './main.component';

import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { HttpClientModule } from '@angular/common/http';

import { httpInterceptorProviders } from '../../util/interceptor/index';

import { LocalStorage } from '../../util/local.storage';


@NgModule({
  imports: [CommonModule, MainRoutingModule, NgZorroAntdModule, HttpClientModule],
  declarations: [MainComponent],
  exports: [MainComponent],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }, httpInterceptorProviders, LocalStorage]

})
export class MainModule { }
