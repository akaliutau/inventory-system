import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {InventoryService} from "../services/inventory.service";
import {debounceTime, distinctUntilChanged, tap} from 'rxjs/operators';
import {fromEvent, merge} from "rxjs";
import {ItemsDatasource} from "../services/items.datasource";


@Component({
  selector: 'inventory',
  templateUrl: './itemList.component.html',
  styleUrls: ['./itemList.component.css']
})
export class ItemListComponent implements OnInit, AfterViewInit {


  dataSource: ItemsDatasource;

  displayedColumns = ["id", "sku", "name", "count"];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  @ViewChild('input', {static: true}) input: ElementRef;

  constructor(private route: ActivatedRoute,
              private inventoryService: InventoryService) {

  }

  ngOnInit() {

    this.dataSource = new ItemsDatasource(this.inventoryService);

    this.dataSource.loadItems(0, 5);

  }

  ngAfterViewInit() {

    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;

          this.loadItemsPage();
        })
      )
      .subscribe();

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => this.loadItemsPage())
      )
      .subscribe();

  }

  loadItemsPage() {
    this.dataSource.loadItems(
      this.paginator.pageIndex,
      this.paginator.pageSize);
  }


}
