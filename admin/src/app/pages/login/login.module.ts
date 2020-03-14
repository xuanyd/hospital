import { NgModule } from '@angular/core';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { IconsProviderModule } from '../../icons-provider.module';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { LocalStorage } from '../../util/local.storage';
import { httpInterceptorProviders } from '../../util/interceptor/index';

import { HttpClientModule } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';

registerLocaleData(zh);

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    LoginRoutingModule,
    IconsProviderModule,
    NgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }, LocalStorage, httpInterceptorProviders],
  bootstrap: [ LoginComponent ]
})
export class LoginModule { }
