import { Injectable } from '@angular/core';
import axios from 'axios';
import { DoterEntity } from '../doter/doter.entity';

@Injectable({
  providedIn: 'root',
})
export class DotersService {
  async getAllDoters(): Promise<DoterEntity[]> {
    const response = await axios.get('http://localhost:8080/doters');
    return response.data;
  }

  async deleteById(id: string) {
    await axios.delete(`http://localhost:8080/doters/${id}`);
  }

  async update(doter: DoterEntity) {
    await axios.put(`http://localhost:8080/doters/${doter.id}`, {
      fullName: doter.fullName,
      hasMother: doter.hasMother,
      didMotherGoToCinema: doter.didMotherGoToCinema,
    })
  }
}
