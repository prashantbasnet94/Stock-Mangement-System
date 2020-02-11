import {AfterContentInit, AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Stock} from '../types/stock';
import {ExpressoService} from '../services/expresso.service';
import {Router} from '@angular/router';
import {forEach} from '@angular/router/src/utils/collection';
import {SorterService} from '../services/sorter.service';
import {ModalDirective} from 'angular-bootstrap-md';

@Component({
  selector: 'app-watchlist-page',
  templateUrl: './watchlist-page.component.html',
  styleUrls: ['./watchlist-page.component.scss']
})
export class WatchlistPageComponent implements OnInit {
  @ViewChild('basicModal') basicModal: ModalDirective;
  @ViewChild('successModal') successModal: ModalDirective;

  stocks: Stock[] = [];
  watchlist: Stock[];
  tickerToAdd = 'No Stock to Add';
  noOfClicks = 0;
  pageLoaded = false;

  constructor(private expressoService: ExpressoService, private router: Router, private sorter: SorterService) {
  }

  ngOnInit() {
    this.expressoService.getStocks()
      .then(() => this.stocks = this.expressoService.stockList);
    this.expressoService.getWatchlist()
      .then(() => {
        this.watchlist = this.expressoService.watchList;
        console.log(this.watchlist);
        this.pageLoaded = true;
      });
  }


  addStockToWatchlist() {
    this.expressoService.addStockToWatchList(this.tickerToAdd)
      .then(() => {
        this.basicModal.hide();
        this.successModal.show();

        setTimeout(() => {
          this.successModal.hide();
        }, 2000);
      })
      .then(() => this.watchlist = this.expressoService.watchList);
  }

  stockDetails(ticker) {
    return this.router.navigate(['/index/' + ticker]);
  }

  sortNumber(value) {
    if (this.noOfClicks % 2 === 0) {
      this.noOfClicks++;
      this.watchlist = this.sorter.sortNumberAscending(this.stocks, value);
    } else if (this.noOfClicks % 2 === 1) {
      this.noOfClicks++;
      this.watchlist = this.sorter.sortNumberDescending(this.stocks, value);
    }
  }


  sortString(value) {
    if (this.noOfClicks % 2 === 0) {
      this.noOfClicks++;
      this.watchlist = this.sorter.sortStringByAscendingOrder(this.stocks, value);
    } else if (this.noOfClicks % 2 === 1) {
      this.noOfClicks++;
      this.watchlist = this.sorter.sortStringByDescendingOrder(this.stocks, value);
    }
  }

}
