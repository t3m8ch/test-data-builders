import {Component, Input, OnInit} from '@angular/core';
import {DoterEntity} from "./doter.entity";

@Component({
  selector: 'app-doter',
  templateUrl: './doter.component.html',
  styleUrls: ['./doter.component.scss']
})
export class DoterComponent implements OnInit {
  @Input() doter: DoterEntity | undefined;

  constructor() {
  }

  ngOnInit(): void {
  }

}
