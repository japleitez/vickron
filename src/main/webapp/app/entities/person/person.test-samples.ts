import dayjs from 'dayjs/esm';

import { IPerson, NewPerson } from './person.model';

export const sampleWithRequiredData: IPerson = {
  id: 31094,
  name: 'Pound Buckinghamshire',
  surname: 'Wooden Sleek',
  email: 'Garret_Hickle@gmail.com',
};

export const sampleWithPartialData: IPerson = {
  id: 31847,
  name: 'Avon',
  surname: 'blockchains',
  email: 'Verla_Baumbach@hotmail.com',
  license: 'Chicken',
};

export const sampleWithFullData: IPerson = {
  id: 54570,
  name: 'Pants Universal',
  surname: 'white',
  email: 'Norene89@yahoo.com',
  license: 'Naira deposit',
  start: dayjs('2023-01-01T14:56'),
  end: dayjs('2023-01-02T05:54'),
};

export const sampleWithNewData: NewPerson = {
  name: 'Forint District',
  surname: 'XML',
  email: 'Kaylie.Yundt86@gmail.com',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
