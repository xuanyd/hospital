import { Component } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { HttpClient } from "@angular/common/http";
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  isCollapsed = false;
  menus: Array<any>;
  loading = false;
  constructor(private http:HttpClient, 
    private titleService: Title,
    private router: Router, 
    private activatedRoute: ActivatedRoute){
  }

  ngOnInit() {
    this.queryMenus();
  }

  queryMenus() {
    let that = this;
    this.http.get("index/menus").subscribe(res=>{
        this.loading = true;
        if(res['code'] === 200) {
            that.menus = res['dataList'];
            console.log(that.menus)
        } else {
            alert(res['message']);
        }
    });
}
}
