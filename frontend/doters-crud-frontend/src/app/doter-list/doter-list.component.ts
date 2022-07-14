import { Component, OnInit } from '@angular/core';
import { DoterEntity } from '../doter/doter.entity';
import { DoterListService } from './doter-list.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-doter-list',
  templateUrl: './doter-list.component.html',
  styleUrls: ['./doter-list.component.scss'],
})
export class DoterListComponent implements OnInit {
  doters: DoterEntity[] = [];
  isLoad: boolean = true;

  constructor(private doterListService: DoterListService, private snackBar: MatSnackBar) {}

  async ngOnInit(): Promise<void> {
    this.doters = await this.doterListService.getAllDoters();
    this.isLoad = false;
  }

  async onDelete(id: string) {
    try {
      await this.doterListService.deleteById(id);
    } catch (e) {
      this.snackBar.open('Failed to delete doter');
      throw e;
    }

    this.snackBar.open('Doter successfully deleted', 'Okay');
    this.doters = this.doters.filter((d) => d.id !== id);
  }
}
