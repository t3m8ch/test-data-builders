import { Component, OnInit } from '@angular/core';
import { DoterEntity } from '../doter/doter.entity';
import { DoterListService } from './doter-list.service';

@Component({
  selector: 'app-doter-list',
  templateUrl: './doter-list.component.html',
  styleUrls: ['./doter-list.component.scss'],
})
export class DoterListComponent implements OnInit {
  doters: DoterEntity[] = [];
  isLoad: boolean = true;

  constructor(private doterListService: DoterListService) {}

  async ngOnInit(): Promise<void> {
    this.doters = await this.doterListService.getAllDoters();
    this.isLoad = false;
  }

  async onDelete(id: string) {
    await this.doterListService.deleteById(id);
    this.doters = this.doters.filter((d) => d.id !== id);
  }
}
