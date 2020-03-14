import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

	constructor(private http: HttpClient) {}

	getUsers(pageIndex: number = 1, pageSize: number = 10, searchParams: any): Observable<{}> {
		let params = searchParams;
		params.page = pageIndex;
		params.pageSize = pageSize;
		return this.http.post(`/user/list-page`, params);
	}

	get(userId: number): Observable<{}> {
		return this.http.get(`user/` + userId);
	}

	edit(params: any): Observable<{}> {
		return this.http.put(`user/edit`, params);
	}

	disable(userId: number): Observable<{}> {
		return this.http.post(`admin/user/disable/` + userId, {});
	}

	enable(userId: number): Observable<{}> {
		return this.http.post(`admin/user/enable/` + userId, {});
	}

}