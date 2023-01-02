import { Type } from 'app/entities/enumerations/type.model';

import { ICoach, NewCoach } from './coach.model';

export const sampleWithRequiredData: ICoach = {
  id: 97953,
  country: 'Algeria',
  name: 'deposit',
  surname: 'Industrial',
  type: Type['FITNESS'],
};

export const sampleWithPartialData: ICoach = {
  id: 4157,
  country: "Lao People's Democratic Republic",
  name: 'overriding copy',
  surname: 'transparent',
  type: Type['FITNESS'],
  email: 'Terrill_Haley@yahoo.com',
  phone: '980.946.8040',
  contact: 'analyzer Salad',
};

export const sampleWithFullData: ICoach = {
  id: 62963,
  country: 'Albania',
  name: 'archive',
  surname: 'Investment azure quantify',
  type: Type['FITNESS'],
  email: 'Tamara_Bode28@yahoo.com',
  phone: '(454) 392-8952',
  resume: 'Planner',
  contact: 'New e-business',
  lang: 'Licensed',
};

export const sampleWithNewData: NewCoach = {
  country: 'Belarus',
  name: 'attitude-oriented Utah auxiliary',
  surname: 'optical Georgia Rustic',
  type: Type['WELLNESS'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
