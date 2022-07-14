import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DoterEntity } from './doter.entity';
import { DotersService } from '../services/doters.service';

type stateType = 'VIEW' | 'EDIT' | 'LOADING';

@Component({
  selector: 'app-doter',
  templateUrl: './doter.component.html',
  styleUrls: ['./doter.component.scss'],
})
export class DoterComponent implements OnInit {
  @Input() doter: DoterEntity | undefined;
  @Output() delete = new EventEmitter();
  @Output() update = new EventEmitter();

  constructor(private _dotersService: DotersService) {}

  private _currentState: stateType = 'VIEW';
  get currentState(): stateType {
    return this._currentState;
  }

  set currentState(value: stateType) {
    this._currentState = value;
    if (value === 'EDIT' && this.doter !== undefined) {
      this._newDoter = { ...this.doter };
    }
    if (value === 'VIEW') {
      this._newDoter = null;
    }
  }

  private _newDoter: DoterEntity | null = null;
  get newDoter(): DoterEntity | null {
    return this._newDoter;
  }

  ngOnInit(): void {}

  async onUpdate() {
    this._currentState = 'LOADING';

    if (!this._newDoter) {
      console.error('this._newDoter is null');
      return;
    }
    await this._dotersService.update(this._newDoter);

    this.doter = this._newDoter;
    this._currentState = "VIEW";
  }
}
