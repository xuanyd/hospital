import { Component, OnInit } from '@angular/core'
import { HttpClient, HttpParams} from '@angular/common/http';
import { Router } from '@angular/router';
import { NoticeService } from '../../../services/notice.service';
import { NzMessageService, NzModalRef,NzModalService} from 'ng-zorro-antd';

@Component({
  selector: 'c-app-notice',
  templateUrl: './notice.component.html',
  styleUrls: ['./notice.component.scss']
})


export class NoticeComponent implements OnInit {

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
      private noticeService: NoticeService,
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
      this.noticeService.getPages(this.pageIndex, this.pageSize, {nameOrPhone:this.username}).subscribe((data: any) => {
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

    delete(idx, name) {
      this.confirmModal = this.modal.confirm({
        nzTitle: '禁用',
        nzContent: '确定删除文章' + name + "吗？",
        nzOnOk: () =>
          new Promise((resolve, reject) => {
          this.noticeService.delete(idx).subscribe((data: any) => {
            if(data.code === 200) {
              this.message.create("success", "删除文章成功！");
              this.confirmModal.close();
              this.queryUsers();
            } else {
              this.message.create("error", "删除文章失败！");
              this.confirmModal.close();
            }
          });        
        }).catch(() => console.log('Oops errors!'))
      });
    }
}