import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class NoticeService {
    constructor(private http: HttpClient) {}
    
	getPages(pageIndex: number = 1, pageSize: number = 10, searchParams: any): Observable<{}> {
		let params = searchParams;
		params.page = pageIndex;
		params.pageSize = pageSize;
		return this.http.post(`/admin/notice/list`, params);
	}

	get(userId: number): Observable<{}> {
		return this.http.get(`user/` + userId);
	}

	edit(params: any): Observable<{}> {
		return this.http.put(`user/edit`, params);
	}

	delete(userId: number): Observable<{}> {
		return this.http.post(`admin/user/disable/` + userId, {});
    }
    
}