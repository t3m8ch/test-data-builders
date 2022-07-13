import {Component, OnInit} from '@angular/core';
import {DoterEntity} from "../doter/doter.entity";
import {DoterListService} from "./doter-list.service";

@Component({
  selector: 'app-doter-list',
  templateUrl: './doter-list.component.html',
  styleUrls: ['./doter-list.component.scss']
})
export class DoterListComponent implements OnInit {
  doters: DoterEntity[] = [];

  constructor(private doterListService: DoterListService) {
    doterListService.getAllDoters().then(d => this.doters = d)
  }

  ngOnInit(): void {
  }
}
