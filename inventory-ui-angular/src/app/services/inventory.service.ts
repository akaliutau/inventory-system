import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable()
export class InventoryService {

  constructor(private http: HttpClient) {

  }

  findItems(pageNumber = 0, pageSize = 5): Observable<Object> {

    return this.http.get('/api/v1/items', {
      params: new HttpParams()
        .set('page', pageNumber.toString())
    });
  }

}
