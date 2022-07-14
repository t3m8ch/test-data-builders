import { Injectable } from '@angular/core';
import axios from 'axios';
import { DoterEntity } from '../doter/doter.entity';

@Injectable({
  providedIn: 'root',
})
export class DoterListService {
  async getAllDoters(): Promise<DoterEntity[]> {
    const response = await axios.get('http://localhost:8080/doters');
    return response.data;
  }

  async deleteById(id: string) {
    await axios.delete(`http://localhost:8080/doters/${id}`);
  }
}
