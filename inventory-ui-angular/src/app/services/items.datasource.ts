import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, Observable, of} from "rxjs";
import {Item} from "../model/item";
import {InventoryService} from "./inventory.service";
import {catchError, finalize, map} from "rxjs/operators";
import {Page} from "../model/page";


export class ItemsDatasource implements DataSource<Item> {

  public pageSubject = new BehaviorSubject<Page>(null);
  private itemsSubject = new BehaviorSubject<Item[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  private res: Observable<Object>;

  constructor(private coursesService: InventoryService) {
  }

  loadItems(pageIndex: number,
              pageSize: number) {

    this.loadingSubject.next(true);

    this.res = this.coursesService.findItems(pageIndex, pageSize);

    this.res
      .subscribe(page => this.pageSubject.next(<Page>page));

    this.res
      .pipe(
        map(res => res["content"])
      )
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(items => this.itemsSubject.next(items));

  }

  connect(collectionViewer: CollectionViewer): Observable<Item[]> {
    console.log("Connecting data source");
    return this.itemsSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.itemsSubject.complete();
    this.loadingSubject.complete();
  }

}

