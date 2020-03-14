import { Component, OnInit } from '@angular/core'
import { HttpClient, HttpParams} from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../../../../services/user.service';
import { NzMessageService, NzModalRef,NzModalService} from 'ng-zorro-antd';

@Component({
  selector: 'c-app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})


export class UserComponent implements OnInit {

    pageIndex = 1;
    pageSize = 10;
    total = 1;
    userList: any[] = [];
    loading: boolean = true;
    username: String;
    confirmModal: NzModalRef;

    constructor(private http:HttpClient, 
        private router: Router,
        private modal: NzModalService,
        private userService: UserService,
        private message: NzMessageService){
    }

    ngOnInit() {
        this.queryUsers();
    }

    queryUsers(reset: boolean = false) {
      let that = this;
      if (reset) {
        that.pageIndex = 1;
      }
      this.loading = true;
      this.userService.getUsers(this.pageIndex, this.pageSize, {nameOrPhone:this.username}).subscribe((data: any) => {
        that.loading = false;
        if(data.code === 200) {
          that.loading = false;
          that.userList = data.data.list;
          that.total = data.total;
          that.pageIndex = 1;
        } else {
          alert(data.message);
        }
      });
    }

    disable(idx, name) {
        this.confirmModal = this.modal.confirm({
            nzTitle: '禁用',
            nzContent: '确定禁用用户' + name + "吗？",
            nzOnOk: () =>
                new Promise((resolve, reject) => {
                this.userService.disable(idx).subscribe((data: any) => {
                    if(data.code === 200) {
                        this.message.create("success", "用户禁用成功！");
                        this.confirmModal.close();
                        this.queryUsers();

                    } else {
                        this.message.create("error", "用户禁用失败！");
                        this.confirmModal.close();
                    }
                });        
            }).catch(() => console.log('Oops errors!'))
        });
    }

    enable(idx, name) {
        this.confirmModal = this.modal.confirm({
            nzTitle: '启用',
            nzContent: '确定启用用户' + name + "吗？",
            nzOnOk: () =>
                new Promise((resolve, reject) => {
                this.userService.enable(idx).subscribe((data: any) => {
                    if(data.code === 200) {
                        this.message.create("success", "用户启用成功！");
                        this.confirmModal.close();
                        this.queryUsers();
                    } else {
                        this.message.create("error", "用户启用失败！");
                        this.confirmModal.close();
                    }
                });        
            }).catch(() => console.log('Oops errors!'))
        });
    }

}