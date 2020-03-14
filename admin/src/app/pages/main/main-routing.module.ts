import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main.component';

const routes: Routes = [{
    path: '',
    component: MainComponent,
    children: <Routes>[
        { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) },
        { path: 'notice', loadChildren: () => import('./notice/notice.module').then(m => m.NoticeModule) }
    ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
